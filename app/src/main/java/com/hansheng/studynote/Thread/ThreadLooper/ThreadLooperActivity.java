package com.hansheng.studynote.Thread.ThreadLooper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-3-6.
 */

public class ThreadLooperActivity extends BaseActivity {
    private static final String TAG = "ThreadLooperActivity";

    Handler handler = null;
    MyHandlerThread mHandlerThread = null;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }


    public void toShape(View view) {

        mHandlerThread = new MyHandlerThread();
        Log.d(TAG, "创建myHandlerThread对象");
        mHandlerThread.start();
        Log.d(TAG, "start一个Thread");

    }

    public void toSelector(View view) {

        if(mHandlerThread.mHandler != null){
            Message msg = new Message();
            msg.what = 1;
            mHandlerThread.mHandler.sendMessage(msg);
        }


    }

    @Override
    protected void initView() {

        Log.d(TAG, Looper.myLooper().toString());
        Log.d(TAG, Looper.getMainLooper().toString());

    }
}

class MyHandlerThread extends Thread {
   private static final String TAG="MyHandlerThread";

    public Handler mHandler = null;

    @Override
    public void run() {
        Log.d(TAG, "进入Thread的run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG, "获得了message");
                super.handleMessage(msg);
            }
        };
        Looper.loop();
    }
}
