package com.hansheng.Threadkey;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hansheng on 16-9-20.
 * 可重入锁最大的作用是避免死锁
 * 一、synchronized和lock的用法区别
 * <p>
 * synchronized：在需要同步的对象中加入此控制，synchronized可以加在方法上，也可以加在特定代码块中，括号中表示需要锁的对象。
 * <p>
 * lock：需要显示指定起始位置和终止位置。一般使用ReentrantLock类做为锁，多个线程中必须要使用一个
 * ReentrantLock类做为对象才能保证锁的生效。
 * 且在加锁和解锁处需要通过lock()和unlock()显示指出。所以一般会在finally块中写unlock()以防死锁。
 *
 * synchronized原始采用的是CPU悲观锁机制
 * Lock用的是乐观锁方式
 * 每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止
 */

public class ReentrantloackTest implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
        System.out.println("unlock");
    }

    private void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
        System.out.println("set unlock");

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
