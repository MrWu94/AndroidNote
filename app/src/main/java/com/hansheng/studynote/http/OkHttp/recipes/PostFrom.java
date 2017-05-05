package com.hansheng.studynote.http.OkHttp.recipes;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/2.
 */
public class PostFrom {
    private final OkHttpClient client=new OkHttpClient();
    public void run() throws IOException {
        RequestBody fromBody=new FormBody.Builder()
                .add("search","Jurassic Park")
                .build();

        Request request=new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(fromBody)
                .build();

        Response response=client.newCall(request).execute();
        System.out.println(response.body().toString());

    }
    public static void main(String... args) throws IOException {
        new PostFrom().run();
    }
}
