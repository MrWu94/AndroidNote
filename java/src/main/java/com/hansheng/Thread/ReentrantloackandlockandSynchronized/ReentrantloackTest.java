package com.hansheng.Thread.ReentrantloackandlockandSynchronized;

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
 *
 * ReentrantLock的lock机制有2种，忽略中断锁和响应中断锁，这给我们带来了很大的灵活性。
 * 比如：如果A、B2个线程去竞争锁，A线程得到了锁，B线程等待，但是A线程这个时候实在有太多事情要处理，
 * 就是一直不返回，B线程可能就会等不及了，想中断自己，不再等待这个锁了，转而处理其他事情。
 * 这个时候ReentrantLock就提供了2种机制，第一，B线程中断自己（或者别的线程中断它），
 * 但是ReentrantLock不去响应，继续让B线程等待，你再怎么中断，我全当耳边风（synchronized原语就是如此）；
 * 第二，B线程中断自己（或者别的线程中断它），
 * ReentrantLock处理了这个中断，并且不再等待这个锁的到来，完全放弃。（如果你没有了解java的中断机制
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
