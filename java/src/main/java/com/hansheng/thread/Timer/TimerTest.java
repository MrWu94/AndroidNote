package com.hansheng.thread.Timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hansheng on 2016/7/15.
 * a、以前在项目中也经常使用定时器，比如每隔一段时间清理项目中的一些垃圾文件，每个一段时间进行数据清洗；
 * 然而Timer是存在一些缺陷的，因为Timer在执行定时任务时只会创建一个线程，
 * 所以如果存在多个任务，且任务时间过长，超过了两个任务的间隔时间，会发生一些缺陷：
 */
public class TimerTest {
    private static long start;

    public static void main(String... args) {
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task1 invoked ! "
                        + (System.currentTimeMillis() - start));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task2 invoked ! "
                        + (System.currentTimeMillis() - start));
            }
        };

        Timer timer = new Timer();
        start = System.currentTimeMillis();
        timer.schedule(task1, 1000);
        timer.schedule(task2, 3000);
    }
}
