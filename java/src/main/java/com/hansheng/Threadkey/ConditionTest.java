package com.hansheng.Threadkey;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hansheng on 16-9-5.
 * 对锁进行精确的控制，可用来代替Object中的wait、notify、notifyAll方法，
 * 需要和Lock联合使用。可以通过await(),signal()来休眠、唤醒线程。
 * 创建方式：Condition condition = lock.newCondition();
 */

public class ConditionTest {

    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public static void main(String... args) {
        ThreadA ta = new ThreadA("ta");
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " start ta");
            ta.start();

            System.out.println(Thread.currentThread().getName() + " block");
            condition.await();    // 等待

            System.out.println(Thread.currentThread().getName() + " continue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();    // 释放锁
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String ta) {
            super(ta);
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " wake others");
                condition.signal();
            } finally {
                lock.unlock();
            }

        }
    }
}
