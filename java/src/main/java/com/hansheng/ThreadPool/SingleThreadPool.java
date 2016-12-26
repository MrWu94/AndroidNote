package com.hansheng.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hansheng on 16-12-26.
 * newSingleThreadExecutor的使用
 * 创建一个单线程的线程池。这个线程只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常而结束，
 * 那么会有一个新的线程来代替它。此线程保证所有的任务的执行顺序按照任务的提交顺序执行。
 */

public class SingleThreadPool {
    public static void main(String... args) {

        ExecutorService pool = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int num = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("start " + num);
                    try {
                        Thread.sleep(100);
                        System.out.println("end " + num);
                    } catch (InterruptedException e) {


                    }

                }
            };
            pool.execute(task);
        }
        pool.shutdown();
        System.out.println("main thread have terminate");
    }
}
