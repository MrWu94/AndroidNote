package com.hansheng.studynote.ui.service.screen;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by hansheng on 17-6-6.
 */

public class ScreenService extends Service {
    public static final String TAG = "ScreenService";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        /* 注册屏幕唤醒时的广播 */
        IntentFilter mScreenOnFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        ScreenService.this.registerReceiver(mScreenOReceiver, mScreenOnFilter);

        /* 注册机器锁屏时的广播 */
        IntentFilter mScreenOffFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        ScreenService.this.registerReceiver(mScreenOReceiver, mScreenOffFilter);
    }

    public void onDestroy() {
        super.onDestroy();
        ScreenService.this.unregisterReceiver(mScreenOReceiver);
    }

    /**
     * 锁屏的管理类叫KeyguardManager，
     * 通过调用其内部类KeyguardLockmKeyguardLock的对象的disableKeyguard方法可以取消系统锁屏，
     * newKeyguardLock的参数用于标识是谁隐藏了系统锁屏
     */
    private BroadcastReceiver mScreenOReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals("android.intent.action.SCREEN_ON")) {
                Log.d(TAG, "onReceive: " + "screen on");
            } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                Log.d(TAG, "onReceive: " + "screen off");
            }
        }

    };

}
