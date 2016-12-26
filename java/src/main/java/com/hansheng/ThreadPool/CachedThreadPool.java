package com.hansheng.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hansheng on 16-12-26.
 * newCachedThreadPool的使用
 * 创建一个缓冲池大小可根据需要伸缩的线程池，但是在以前构造的线程可用时将重用它们。对于执行很多短期异步任务而言，
 * 这些线程池通常可提供程序性能。调用execute将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，
 * 则创建一个新线程并添加到池中。终止并从缓存中移除那些已有60s未被使用的线程。因此，长时间保持空闲的线程池不会使用任何资源。

 */

public class CachedThreadPool {
    public static void main(String... args) {
        ExecutorService pool = Executors.newCachedThreadPool();

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
        System.out.println("");
        System.out.println("main thread have terminate");
    }

}
