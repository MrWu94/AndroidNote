package com.hansheng.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。

        //ExecutorService cachedThreadPool=Executors.newCachedThreadPool();
        //	创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {

                public void run() {
                    // TODO Auto-generated method stub
                    System.out.println(index);

                }
            });
        }
        //创建一个定长线程池，支持定时及周期性任务执行。
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {

                public void run() {
                    // TODO Auto-generated method stub
                    System.out.println(index);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }

    }


}
