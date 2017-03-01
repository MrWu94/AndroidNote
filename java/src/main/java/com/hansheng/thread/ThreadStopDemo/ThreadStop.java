package com.hansheng.thread.ThreadStopDemo;

import java.util.concurrent.TimeUnit;

/**
 * Created by hansheng on 17-3-1.
 * 由于没有同步，就不能保证后台线程何时“看到”主线程对stopRequested的值的改变，，没有同步
 * 虚拟机将这个代码
 * while(!done){
 *     i++
 * }
 * if(!done){
 *     while(true){
 *         i++;
 *     }
 * }
 */

public class ThreadStop {

    private static boolean stopRequested;
    public static void main(String... args) throws InterruptedException {

        Thread backgroundThread=new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (!stopRequested){
                    i++;
                    System.out.println(i);
                }
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested=true;
    }
}
