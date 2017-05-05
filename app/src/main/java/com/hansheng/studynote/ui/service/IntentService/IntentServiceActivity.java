package com.hansheng.studynote.ui.service.IntentService;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-11-28.
 * 、IntentService简介
 * IntentService是Service的子类，比普通的Service增加了额外的功能。先看Service本身存在两个问题：
 * Service不会专门启动一条单独的进程，Service与它所在应用位于同一个进程中；
 * Service也不是专门一条新线程，因此不应该在Service中直接处理耗时的任务；
 * <p>
 * 二、IntentService特征
 * 会创建独立的worker线程来处理所有的Intent请求；
 * 会创建独立的worker线程来处理onHandleIntent()方法实现的代码，无需处理多线程问题；
 * 所有请求处理完成后，IntentService会自动停止，无需调用stopSelf()方法停止Service；
 * 为Service的onBind()提供默认实现，返回null；
 * 为Service的onStartCommand提供默认实现，将请求Intent添加到队列中；
 */

public class IntentServiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentservice_main);
        Log.i("Tag1",getCurrentProgressName(getApplicationContext()));
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


    public void startService(View source) {
        // 创建所需要启动的Service的Intent
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void startIntentService(View source) {
        // 创建需要启动的IntentService的Intent
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

    public void text(View view){
        Intent intent=new Intent(this,TestIntentService.class);
        startService(intent);
    }
}
