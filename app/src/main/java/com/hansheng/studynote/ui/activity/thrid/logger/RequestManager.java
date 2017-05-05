package com.hansheng.studynote.ui.activity.thrid.logger;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.hansheng.studynote.ui.activity.thrid.logger.db.DBManager;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner;

/**
 * 管理所有请求类
 * Created by dongjunkun on 2016/2/1.
 */
public class RequestManager {
    private static final String ERROR = "error";
    private static final String RESULTS = "results";
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void get(Object tag, final String url, final CallBack callBack) {
        get(tag, url, false, callBack);
    }

    /**
     * 获取列表数据请求
     *
     * @param tag  标签，唯一标识
     * @param url
     * @param type
     */
    public static void getList(Object tag, final String url, final Type type, final boolean isCache) {


        Request request = new Request.Builder()
                .tag(tag)
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (!call.isCanceled()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            listener.onFailure(4, e.getLocalizedMessage());
                        }
                    });
                }
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                String json = response.body().string();

                String authorization = response.header("Authorization");
                if (authorization != null) {
//                    PreferencesUtils.putString(App.getContext(), "Authorization", "Beare " + authorization);
                }
                final String finalJson = json;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Logger.json(finalJson);
                            //转化为json对象
                            JSONObject jsonObject = new JSONObject(finalJson);
                            //判断error字段是否存在，不存在返回失败信息并结束请求
                            if (jsonObject.isNull(ERROR)) {
//                                listener.onFailure(0, "error key not exists!!");
                                return;
                            }
                            //判断后台返回结果，true表示失败，false表示成功，失败则返回错误回调并结束请求
                            if (jsonObject.getBoolean(ERROR)) {
//                                listener.onFailure(0, "request failure!!");
                                return;
                            }
                            //判断results字段是否存在，不存在返回时报回调并结束请求
                            if (jsonObject.isNull(RESULTS)) {
//                                listener.onFailure(0, "results key not exists!!");
                                return;
                            }
                            //获取results的值
                            String results = jsonObject.getString(RESULTS);


                            //返回成功回调
                            Object o = new Gson().fromJson(results, newParameterizedTypeWithOwner(null, ArrayList.class, type));


                        } catch (JSONException e) {

                        }
                    }
                });
            }
        });
    }


    public static void get(Object tag, final String url, final boolean isCache, final CallBack callBack) {
        //读取缓存数据
        final DBManager dbManager = new DBManager();
        String data = dbManager.getData(url);
        if (!"".equals(data)) {
            //解析json数据并返回成功回调
            Logger.json(data);

            callBack.onSuccess(new Gson().fromJson(data, callBack.type));
        }


        //初始化请求对象
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();

        //像服务器发送异步请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.getLocalizedMessage());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取String类型响应，注意是string(),不是toString()
                final String json = response.body().string();
                //在控制台格式化打印json数据
//                Logger.json(json);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        handleResponse(json, callBack, dbManager, url, isCache);
                    }
                });

            }
        });
    }


    /**
     * 处理请求结果
     *
     * @param json
     * @param callBack
     * @param url
     */
    private static void handleResponse(String json, CallBack callBack, DBManager dbManager, String url, boolean isCache) {
        try {
            //转化为json对象
            JSONObject jsonObject = new JSONObject(json);
            //判断error字段是否存在，不存在返回失败信息并结束请求
            if (jsonObject.isNull(ERROR)) {
                callBack.onFailure("error key not exists!!");
                return;
            }
            //判断后台返回结果，true表示失败，false表示成功，失败则返回错误回调并结束请求
            if (jsonObject.getBoolean(ERROR)) {
                callBack.onFailure("request failure!!");
                return;
            }
            //判断results字段是否存在，不存在返回时报回调并结束请求
            if (jsonObject.isNull(RESULTS)) {
                callBack.onFailure("results key not exists!!");
                return;
            }
            //获取results的值
            String results = jsonObject.getString(RESULTS);
            if (isCache) {
                //插入缓存数据库
                dbManager.insertData(url, results);
            }

            //返回成功回调
            callBack.onSuccess(new Gson().fromJson(results, callBack.type));
        } catch (JSONException e) {
            callBack.onFailure(e.getLocalizedMessage());
        }
    }

    /**
     * 根据tag取消请求
     *
     * @param tag 标签
     */
    public static void cancelRequest(Object tag) {
        for (Call call : okHttpClient.dispatcher().queuedCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
        for (Call call : okHttpClient.dispatcher().runningCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
    }

    /**
     * 请求响应日志信息，方便debug
     */
    public static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Logger.i(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Logger.i(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
}