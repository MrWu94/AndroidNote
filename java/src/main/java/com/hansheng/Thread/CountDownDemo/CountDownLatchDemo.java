package com.hansheng.Thread.CountDownDemo;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hansheng on 16-12-27.
 * CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。比如有一个任务A，
 * 它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 */

public class CountDownLatchDemo {
    public static void main(String... args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(2);
        new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {

            @Override
            public void run() {
                super.run();
                System.out.println("子线程" + Thread.currentThread().getName() + "只在执行");
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        System.out.println("等待2个子线程执行完毕");
        latch.await();
        System.out.println("2个子线程执行完毕");
        System.out.println("继续执行主线程");


    }
}
