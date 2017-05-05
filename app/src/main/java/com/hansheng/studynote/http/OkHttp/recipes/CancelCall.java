package com.hansheng.studynote.http.OkHttp.recipes;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/2.
 */
public class CancelCall {
    private final ScheduledExecutorService executorService= Executors.newScheduledThreadPool(1);
    private final OkHttpClient client=new OkHttpClient();

    public void run(){
        Request request=new Request.Builder()
                .url("http://httpbin.org/delay/2")
                .build();

        final long start=System.nanoTime();
        final Call call=client.newCall(request);

        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - start) / 1e9f);
                call.cancel();
                System.out.printf("%.2f Canceled call.%n", (System.nanoTime() - start) / 1e9f);
            }
        },1, TimeUnit.SECONDS);


        System.out.printf("%.2f Executing call.%n", (System.nanoTime() - start) / 1e9f);
        try {
            Response response=call.execute();
            System.out.printf("%.2f Call was expected to fail, but completed: %s%n",
                    (System.nanoTime() - start) / 1e9f, response);
        } catch (IOException e) {
            System.out.printf("%.2f Call failed as expected: %s%n",
                    (System.nanoTime() - start) / 1e9f, e);
        }

    }
    public static void  main(String... args){
        new CancelCall().run();
    }


}
