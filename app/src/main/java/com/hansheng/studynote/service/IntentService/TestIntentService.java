package com.hansheng.studynote.service.IntentService;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by hansheng on 16-11-28.
 * 对于startService()请求执行onHandleIntent()中的耗时任务，会生成一个队列，
 * 每次只有一个Intent传入onHandleIntent()方法并执行。也就是同一时间只会有一个耗时任务被执行，其他的请求还要在后面排队，
 * onHandleIntent()方法不会多线程并发执行。
 * 3、当所有startService()请求被执行完成后，IntentService 会自动销毁，所以不需要自己写stopSelf()或stopService()来销毁服务。
 * 多次startService请求执行耗时任务，不会并发执行onHandleIntent()方法，而是一个一个顺序执行。当所有的任务执行完成，
 * IntentService会自动销毁。
 */

public class TestIntentService extends IntentService {

    public TestIntentService() {
        super("TestIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        DateFormat format = DateFormat.getTimeInstance();
        Log.v("test", "onHandleIntent开始:" + format.format(new Date()));

        // 这里Thread.sleep(5000)模拟执行一个耗时5秒的任务
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        Log.v("test", "onHandleIntent完成:" + format.format(new Date()));
    }

    // 这里还重写了onDestroy，记录日志用于观察Service何时销毁
    @Override
    public void onDestroy() {
        Log.v("test", "onDestroy");
        super.onDestroy();
    }
}