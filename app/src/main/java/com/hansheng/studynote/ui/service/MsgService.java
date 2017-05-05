package com.hansheng.studynote.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hansheng on 2016/6/29.
 * Service和Activity通信
 * 面我们来看下通过继承Binder类实现客户端与服务端通信应该怎样做：
 * <p>
 * 在service类中，创建一个满足以下任一要求的Binder实例：
 * 包含客户端可调用的公共方法
 * 返回当前Service实例，其中包含客户端可调用的公共方法
 * 返回由当前service承载的其他类的实例，其中包含客户端可调用的公共方法
 * 在onBind()方法中返回这个Binder实例
 * 在客户端中通过onServiceDisconnected()方法接收传过去的Binder实例，并通过它提供的方法进行后续操作
 */
public class MsgService extends Service {


    public static final int MAX_PROGRESS = 100;

    private OnProgressListener onProgressListener;

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    private int progress = 0;

    public int getProgress() {
        return progress;
    }

    public void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < MAX_PROGRESS) {
                    progress += 5;
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

    public class MsgBinder extends Binder {
        public MsgService getService() {
            return MsgService.this;
        }
    }

    public interface OnProgressListener {
        void onProgress(int progress);
    }
}
