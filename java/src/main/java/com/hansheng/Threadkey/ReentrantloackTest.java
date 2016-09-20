package com.hansheng.Threadkey;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hansheng on 16-9-20.
 * 可重入锁最大的作用是避免死锁
 */

public class ReentrantloackTest implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    private void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();

    }

    @Override
    public void run() {
        get();
    }

    public static void main(String... args) {
        ReentrantloackTest reentrantlock = new ReentrantloackTest();
        new Thread(reentrantlock).start();
        new Thread(reentrantlock).start();
        new Thread(reentrantlock).start();
        new Thread(reentrantlock).start();
    }
}
