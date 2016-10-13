package com.hansheng.studynote.OkHttp;

import android.util.Log;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hansheng on 16-10-13.
 *  * ##拦截器有什么作用
 * 先来看看Interceptor本身的文档解释：观察，修改以及可能短路的请求输出和响应请求的回来。
 * 通常情况下拦截器用来添加，移除或者转换请求或者回应的头部信息。
 * 拦截器接口中有intercept(Chain chain)方法，同时返回Response。
 */

public class LoggingInterceptors {

    private final OkHttpClient client = new OkHttpClient();

    public LoggingInterceptors() {
        client.networkInterceptors().add(new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                long t1 = System.nanoTime();
                Request request = chain.request();
                System.out.println(String.format("Sending request %s on %s%n%s")+
                        request.url().toString());
                Log.d(String.format("Sending request %s on %s%n%s"),
                        request.url().toString());
                Response response = chain.proceed(request);

                long t2 = System.nanoTime();
//                logger.info(String.format("Received response for %s in %.1fms%n%s",
//                        request.url(), (t2 - t1) / 1e6d, response.headers()));
                return response;
            }
        });
    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html")
                .build();

        Response response = client.newCall(request).execute();
        response.body().close();
    }

    public static void main(String... args) throws Exception {
        new LoggingInterceptors().run();
    }
}
