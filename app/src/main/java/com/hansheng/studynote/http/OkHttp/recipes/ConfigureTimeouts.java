package com.hansheng.studynote.http.OkHttp.recipes;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/2.
 */
public class ConfigureTimeouts {
    private final OkHttpClient client;
    public ConfigureTimeouts(){
        client=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();
    }
    public void run() throws IOException {
        Request request=new Request.Builder()
                .url("http://httpbin.org/delay/2")
                .build();
        Response response=client.newCall(request).execute();
        System.out.println("Response conpleted"+response);

    }
    public static void main(String... args) throws IOException {
        new ConfigureTimeouts().run();
    }
}
