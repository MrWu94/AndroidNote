package com.hansheng.Thread.Threadkey;

/**
 * Created by hansheng on 16-9-20.
 * 锁作为并发共享数据，
 * 保证一致性的工具，在JAVA平台有多种实现(如 synchronized 和 ReentrantLock等等是广义上的可重入锁，
 * 而不是单指JAVA下的ReentrantLock。
 * 可重入锁，也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。
 * 在JAVA环境下 ReentrantLock 和synchronized 都是 可重入锁
 */
public class Test implements Runnable {

    public synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set() {
        System.out.println(Thread.currentThread().getId());
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Test ss = new Test();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}
