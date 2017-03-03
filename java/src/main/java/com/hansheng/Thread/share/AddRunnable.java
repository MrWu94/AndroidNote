package com.hansheng.Thread.share;

/**
 * Created by hansheng on 2016/7/29.
 */
public class AddRunnable implements Runnable {
    MyData data;

    public AddRunnable(MyData data) {
        this.data = data;
    }

    @Override
    public void run() {
        data.add();
    }
}
