package com.hansheng.studynote.http.OkHttp.recipes;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by hansheng on 2016/7/2.
 */
public class SynchronousGet {

    private final OkHttpClient client=new OkHttpClient();
    public void run() throws IOException {
        Request request=new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
        Response response=client.newCall(request).execute();
        Headers responseheaders=response.headers();
        for(int i=0;i<responseheaders.size();i++){
            System.out.println(responseheaders.name(i)+":"+responseheaders.value(i));

        }
        System.out.println(response.body().toString());
    }
    public static void main(String... args) throws IOException {
        new SynchronousGet().run();
    }

}
