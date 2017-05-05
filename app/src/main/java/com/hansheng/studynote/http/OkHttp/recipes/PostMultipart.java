package com.hansheng.studynote.http.OkHttp.recipes;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/2.
 */
public class PostMultipart {
    private static final String IMGUR_CLIENT_ID = "9199fdef135c122";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private final OkHttpClient client=new OkHttpClient();
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void run(){
        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title","Square Logo")
//                .addFormDataPart("image","logo-suqare.png",RequestBody.create(MEDIA_TYPE_PNG,new File("website/static/logo-square.png")))
                .build();
        Request request=new Request.Builder()
                .header("Authorization","Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String... args) throws Exception {
        new PostMultipart().run();
    }

}
