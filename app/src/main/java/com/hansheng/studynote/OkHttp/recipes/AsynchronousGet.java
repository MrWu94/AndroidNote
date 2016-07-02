package com.hansheng.studynote.OkHttp.recipes;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.framed.Header;

/**
 * Created by hansheng on 2016/7/2.
 */
public class AsynchronousGet {

    private static final OkHttpClient client=new OkHttpClient();
    public static void run(){
        final Request request=new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                RequestBody responseBody=request.body();
                if(!response.isSuccessful())throw new IOException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(responseBody.toString());

            }
        });
    }
    public static void main(String... args){
        run();
    }
}
