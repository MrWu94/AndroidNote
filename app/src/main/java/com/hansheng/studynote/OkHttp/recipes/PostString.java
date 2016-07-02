package com.hansheng.studynote.OkHttp.recipes;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/2.
 */
public class PostString {
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final OkHttpClient client=new OkHttpClient();
    public static void run() throws IOException {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        System.out.println(postBody.toString());
        Request request=new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,postBody))
                .build();

        Response response=client.newCall(request).execute();
        System.out.println(response.body().toString());
    }
    public static void main(String... args) throws IOException {
        run();
    }
}
