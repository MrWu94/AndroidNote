package com.hansheng.Thread.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hansheng on 16-12-26.创建一个可重用固定线程数的线程池，
 * 以共享的无界队列方式来运行这些线程。在任意点，在大多数nThreads线程会处于处理任务的活动状态。
 * 如果在所有线程处于活动状态时提交附加任务，则在有可用线程之前，附加任务将在队列中等待。如果在关闭前的执
 * 行期间由于失败而导致任何线程终止，那么一个新的线程将代替它执行后续任务（如果需要）。在某个线程被显示关闭之前，池中的线程将一直存在。
 */

public class FixedThreadPool {
    public static void main(String... args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int num = 0;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("start " + num);
                    try {
                        Thread.sleep(100);
                        System.out.println("stop " + num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            pool.execute(task);

        }
        pool.shutdown();
        System.out.println("main thread have terminate");
    }
}
