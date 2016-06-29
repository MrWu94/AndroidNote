package com.hansheng.studynote.broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hansheng on 2016/6/29.
 */
public class MsgService extends Service {

    /**
     * 进度条的最大值
     */
    public static final int MAX_PROGRESS = 100;
    /**
     * 进度条的进度值
     */
    private int progress = 0;

    private Intent intent=new Intent("com.hansheng.studynote.RECEIVER");

    public void startDownLoad(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progress<MAX_PROGRESS){
                    progress+=5;
                    intent.putExtra("progress",progress);
                    sendBroadcast(intent);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startDownLoad();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
