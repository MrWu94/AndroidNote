package com.hansheng.Thread.ThreadStopDemo;

import java.util.concurrent.TimeUnit;

/**
 * Created by hansheng on 17-3-1.
 */

public class StopThread {

    private static boolean stopRequested;

    private static synchronized void requestedStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String... args) throws InterruptedException {

        Thread backGroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (!stopRequested()) {
                    i++;
                    System.out.println(i);
                }
            }
        });
        backGroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        requestedStop();

    }
}
