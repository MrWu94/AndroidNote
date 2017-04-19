package com.hansheng.studynote.Http.OkHttp.recipes;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/2.
 */
public class PostFile {
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final OkHttpClient client=new OkHttpClient();

    public static void run() throws IOException {
        File file=new File("C:\\Users\\hansheng\\Desktop\\SimpleNBA\\README.md");
        Request request=new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,file))
                .build();
        Response response=client.newCall(request).execute();
        System.out.println(response.body().toString());
    }
    public static void main(String... args) throws IOException {
        run();
    }
}
