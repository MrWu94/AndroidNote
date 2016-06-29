package com.hansheng.studynote.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hansheng on 2016/6/29.
 */
public class MsgService extends Service {


    public static final int MAX_PROGRESS=100;

    private OnProgressListener onProgressListener;

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    private int progress=0;

    public int getProgress(){
        return progress;
    }

    public void startDownload(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress<MAX_PROGRESS){
                    progress+=5;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MsgBinder();
    }

    public class MsgBinder extends Binder{
       public  MsgService getService(){
           return MsgService.this;
       }
    }

    public interface OnProgressListener {
        void onProgress(int progress);
    }
}
