package com.hansheng.studynote.service.IntentService;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by hansheng on 16-11-28.
 */

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 该方法内执行耗时任务可能导致ANR(Application Not Responding)异常
        long endTime = System.currentTimeMillis() + 20 * 1000;
        System.out.println("onStart");
        Log.i("Tag2",getCurrentProgressName(getApplicationContext()));
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
        return START_STICKY;
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