package com.hansheng.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by hansheng on 16-9-28.
 * Java并发包中的信号量Semaphore实际上是一个功能完毕的计数信号量，从概念上讲，它维护了一个许可集合，
 * 对控制一定资源的消费与回收有着很重要的意义。Semaphore可以控制某个资源被同时访问的任务数，
 * 它通过acquire（）获取一个许可，release（）释放一个许可。
 * 如果被同时访问的任务数已满，则其他acquire的任务进入等待状态，直到有一个任务被release掉，它才能得到许可。
 * 可以看出，Semaphore允许并发访问的任务数一直为5，当然，这里还很容易看出一点，
 * 就是Semaphore仅仅是对资源的并发访问的任务数进行监控，而不会保证线程安全，因此，在访问的时候，要自己控制线程的安全访问。
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //采用新特性来启动和管理线程——内部使用线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //只允许5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        //模拟10个客户端访问
        for (int index = 0; index < 10; index++) {
            final int num = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        //获取许可
                        semp.acquire();
                        System.out.println("线程" +
                                Thread.currentThread().getName() + "获得许可：" + num);
                        //模拟耗时的任务
                        for (int i = 0; i < 999999; i++) ;
                        //释放许可
                        semp.release();
                        System.out.println("线程" +
                                Thread.currentThread().getName() + "释放许可：" + num);
                        System.out.println("当前允许进入的任务个数：" +
                                semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        //关闭线程池
        exec.shutdown();
    }
}