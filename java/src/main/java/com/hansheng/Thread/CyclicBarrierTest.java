package com.hansheng.Thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by hansheng on 16-9-28.
 * CyclicBarrier（又叫障碍器）同样是Java 5中加入的新特性，使用时需要导入java.util.concurrent.CylicBarrier。
 * 它适用于这样一种情况：你希望创建一组任务，它们并发地执行工作，另外的一个任务在这一组任务并发执行结束前一直阻塞等待，
 * 直到该组任务全部执行结束，这个任务才得以执行。这非常像CountDownLatch，只是CountDownLatch是只触发一次的事件，
 * 而CyclicBarrier可以多次重用。
 */

public class CyclicBarrierTest {
    public static void main(String[] args) {
        //创建CyclicBarrier对象，
        //并设置执行完一组5个线程的并发任务后，再执行MainTask任务
        CyclicBarrier cb = new CyclicBarrier(5, new MainTask());
        new SubTask("A", cb).start();
        new SubTask("B", cb).start();
        new SubTask("C", cb).start();
        new SubTask("D", cb).start();
        new SubTask("E", cb).start();
    }
}

/**
 * 最后执行的任务
 */
class MainTask implements Runnable {
    public void run() {
        System.out.println("......终于要执行最后的任务了......");
    }
}

/**
 * 一组并发任务
 */
class SubTask extends Thread {
    private String name;
    private CyclicBarrier cb;

    SubTask(String name, CyclicBarrier cb) {
        this.name = name;
        this.cb = cb;
    }

    public void run() {
        System.out.println("[并发任务" + name + "]  开始执行");
        for (int i = 0; i < 999999; i++) ;    //模拟耗时的任务
        System.out.println("[并发任务" + name + "]  开始执行完毕，通知障碍器");
        try {
            //每执行完一项任务就通知障碍器
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
