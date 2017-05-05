package com.hansheng.studynote.ui.service.IntentService;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hansheng on 16-11-28.
 * * 在这里我们可以清楚的看到其实IntentService在执行onCreate的方法的时候，其实开了一个线程HandlerThread,并获得了当前线程队列管理的looper
 * ，并且在onStart的时候，把消息置入了消息队列，在消息被handler接受并且回调的时候，执行了onHandlerIntent方法，该方法的实现是子类去做的
 * 结论：IntentService是通过Handler looper message的方式实现了一个多线程的操作，同时耗时操作也可以被这个线程管理和执行，
 * 同时不会产生ANR的情况。
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // IntentService会使用单独的线程来执行该方法的代码
        // 该方法内执行耗时任务，比如下载文件，此处只是让线程等待20秒
        long endTime = System.currentTimeMillis() + 20 * 1000;
        System.out.println("onStart");
        Log.i("Tag3", getCurrentProgressName(getApplicationContext()));
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("----耗时任务执行完成---");
    }

    public static String getCurrentProgressName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;

    }
}