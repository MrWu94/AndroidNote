package com.hansheng.studynote.OkHttp.recipes;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/2.
 */
public class AccessHeaders {
    private static final OkHttpClient client=new OkHttpClient();
    public static void run() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();
        Response response=client.newCall(request).execute();
        if(!response.isSuccessful()){
            System.out.println("Server:"+response.header("Server"));
            System.out.println("Date:"+response.header("Date"));
            System.out.println("Vary:"+response.header("Vary"));
        }
    }
    public static void main(String... arags) throws IOException {
        run();
    }
}
