package com.hansheng.Threadkey;

/**
 * Created by hansheng on 16-9-5.
 *
 * synchronized(this)以及非static的synchronized方法（至于static synchronized方法请往下看），
 * 只能防止多个线程同时执行同一个对象的同步代码段。
 * 用synchronized(Sync.class)实现了全局锁的效果。
 *
 * synchronized锁住的是代码还是对象。答案是：synchronized锁住的是括号里的对象，而不是代码。
 * 对于非static的synchronized方法，锁的就是对象本身也就是this。

 最后说说static synchronized方法，static方法可以直接类名加方法名调用，方法中无法使用this，所以它锁的不是this，
 而是类的Class对象，所以，static synchronized方法也相当于全局锁，相当于锁住了代码段。
 */
class Sync {

    public void test() {
        synchronized (Sync.class) {
            System.out.println("test开始..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test结束..");
        }
    }
}

class MyThread extends Thread {

    public void run() {
        Sync sync = new Sync();
        sync.test();
    }
}

 class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new MyThread();
            thread.start();
        }
    }
}