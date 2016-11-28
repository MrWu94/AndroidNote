package com.hansheng.studynote.service.IntentService;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hansheng on 16-11-28.
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
        Log.i("Tag3",getCurrentProgressName(getApplicationContext()));
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
    public static String getCurrentProgressName(Context context)
    {
        int pid=android.os.Process.myPid();
        ActivityManager mActivityManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningAppProcessInfo appProcess:mActivityManager.getRunningAppProcesses())
        {
            if(appProcess.pid==pid)
            {
                return appProcess.processName;
            }
        }
        return null;

    }
}