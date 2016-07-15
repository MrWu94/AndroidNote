package com.hansheng.thread.Timer;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by hansheng on 2016/7/15.
 * 因为ScheduledThreadPool内部是个线程池，所以可以支持多个任务并发执行
 */
public class ScheduledThreadPoolExecutorTest {


    private static long start;

    public static void main(String... args){
        ScheduledExecutorService newSchedledThreadPool= Executors.newScheduledThreadPool(2);

        TimerTask task1=new TimerTask() {
            @Override
            public void run() {
                try
                {

                    System.out.println("task1 invoked ! "
                            + (System.currentTimeMillis() - start));
                    Thread.sleep(3000);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };


        TimerTask task2 = new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("task2 invoked ! "
                        + (System.currentTimeMillis() - start));
            }
        };

        start= System.currentTimeMillis();
        newSchedledThreadPool.schedule(task1,1000, TimeUnit.MILLISECONDS);
        newSchedledThreadPool.schedule(task2,3000,TimeUnit.MILLISECONDS);

    }
}
