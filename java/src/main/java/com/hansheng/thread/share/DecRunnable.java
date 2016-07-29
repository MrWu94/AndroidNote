package com.hansheng.thread.share;

/**
 * Created by hansheng on 2016/7/29.
 */
public class DecRunnable implements Runnable {
    MyData data;

    public DecRunnable(MyData data) {
        this.data = data;
    }

    @Override
    public void run() {
        data.dec();
    }
}
