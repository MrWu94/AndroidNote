package com.hansheng.studynote.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hansheng.studynote.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wfq on 2016/11/28.
 * Timer比Handler更占内存。
 * 相比起Timer方式的定时器占用1192B，Handler的方式占用资源会小很多，只有1/60。
 * <p>
 * 所以Handler的方式比较节省内存。
 * <p>
 * 在Activity由前台转后台过程中，线程是一直在运行的，但是当Activity转入前台时会重新定义Runnable runnable；
 * 也就是说此时从message queue移除的runnable与原先加入message queue中的runnable并非是同一个对象。
 * 如果把runnable定义为静态的则removeCallbacks不会失效,对于静态变量在内存中只有一个拷贝（节省内存），
 * JVM只为静态分配一次内存，在加载类的过程中完成静态变量的内存分配
 */

public class TimerAndHandlerActivity extends AppCompatActivity {
    int mCount = 0;
    static Handler handler = new Handler();
    static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable, 1000);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        startHandler();
//        startTimer();

    }


    public void sTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 0, 100);

    }


    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("JKL", "" + mCount);
                mCount++;
            }
        }, 0, 200);
    }

    private void startHandler() {
        final Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("JKL", "" + mCount);
                mCount++;
//                要做的事情，这里再次调用此Runnable对象，以实现每两秒实现一次的定时器操作
                mHandler.postDelayed(this, 200);
            }
        });
    }

    private void startHandler1() {
        HandlerThread thread = new HandlerThread("Test");
        thread.start();
        final Handler mHandler = new Handler(thread.getLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("JKL", "" + mCount);
                mCount++;
                mHandler.postDelayed(this, 200);
            }
        });
    }
}
