package com.hansheng.studynote.memoryleak.checkoutthread;

import android.os.Looper;
import android.support.compat.BuildConfig;

/**
 * Created by mrwu on 2017/6/20.
 */

public class CheckWorkerThread {
    public void checkWorkerThread() {
        boolean isMainThread = Looper.myLooper() == Looper.getMainLooper();
        if (isMainThread) {
            if (BuildConfig.DEBUG) {
                throw new RuntimeException("Do not do time-consuming work in the Main thread");
            }
        }
    }
}
