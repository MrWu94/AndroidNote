package com.hansheng.studynote.OkHttp.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/6.
 */
public class OkHttpUtils {

    private static final String TAG="OkHttpUtils";

    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    private OkHttpUtils(){
        mOkHttpClient= new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();
        mDelivery=new Handler(Looper.getMainLooper());
    }
    private synchronized static OkHttpUtils getmInstance(){
        if(mInstance==null){
            mInstance=new OkHttpUtils();
        }
        return mInstance;
    }

    private void getRequest(String url,final ResultCallback callback){
        
        final Request request=new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback,request);
    }

    private void postRequest(String url,final ResultCallback callback,List<Param> params){
        Request request=buildPostRequest(url,params);
        deliveryResult(callback,request);
    }

    private Request buildPostRequest(String url, List<Param> params) {

        FormBody.Builder form=new FormBody.Builder();
        for(Param param:params){
           form.addEncoded(param.key,param.value);

        }
        FormBody formBody=form.build();
        return new Request.Builder()
                .url(url)
                .post(formBody)
                .build();



    }

    private void deliveryResult(final ResultCallback callback, final Request request) {
        
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailCallback(callback,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                
                String str=response.body().string();
                if(callback.mtype==String.class){
                    sendSuccessCallBack(callback,str);
                }


            }
        });
    }

    private void sendSuccessCallBack(final ResultCallback callback, final String str) {

        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onSuccess(str);
                }
            }
        });
    }

    private void sendFailCallback(final ResultCallback callback,final IOException e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onFailure(e);
                }
            }
        });
    }

    public static void get(String url,ResultCallback callback){
        getmInstance().getRequest(url,callback);
    }

    public static void post(String url,final ResultCallback callback,List<Param> params){
        getmInstance().postRequest(url,callback,params);
    }

    public static abstract class ResultCallback<T>{

        Type mtype;

        public ResultCallback(){
            mtype=getSuperclassTypeParamter(getClass());
        }
        
        static Type getSuperclassTypeParamter(Class<?> subclass){
            Type superclass=subclass.getGenericSuperclass();
            if(superclass instanceof Class){
                throw new RuntimeException("Missing type parameter");
            }
            ParameterizedType parameterizedType= (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
        }
        
        public abstract void onSuccess(T response);
        public abstract void onFailure(Exception e);
    }

    public static class Param{
        String key;
        String value;

        public Param(){}
        public Param(String key,String value){
            this.key=key;
            this.value=value;
        }
    }
}
