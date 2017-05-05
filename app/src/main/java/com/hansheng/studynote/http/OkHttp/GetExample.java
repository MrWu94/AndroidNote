package com.hansheng.studynote.http.OkHttp;



import android.annotation.TargetApi;
import android.os.Build;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/2.
 */
public class GetExample {

    OkHttpClient client=new OkHttpClient();
    @TargetApi(Build.VERSION_CODES.KITKAT)
    String run(String url) throws IOException {
        Request request=new Request.Builder()
                .url(url)
                .build();
       Response response=client.newCall(request).execute();
            return response.body().toString();

    }
    public static void main(String... args) throws IOException {
        GetExample example=new GetExample();
        String response=example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }
}
