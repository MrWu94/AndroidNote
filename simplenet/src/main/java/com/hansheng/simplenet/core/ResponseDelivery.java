package com.hansheng.simplenet.core;

import android.os.Handler;
import android.os.Looper;

import com.hansheng.simplenet.base.Request;
import com.hansheng.simplenet.base.Response;

import java.util.concurrent.Executor;

/**
 * Created by hansheng on 2016/8/20.
 */
public class ResponseDelivery implements Executor {


    Handler mResponseHandler=new Handler(Looper.getMainLooper());



    public void deliveryResponse(final Request<?> request, final Response response) {

        Runnable respRunnable=new Runnable() {
            @Override
            public void run() {
                request.deliveryResponse(response);
            }
        };
        execute(respRunnable);
    }

    @Override
    public void execute(Runnable command) {

        mResponseHandler.post(command);

    }
}
