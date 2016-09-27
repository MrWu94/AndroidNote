package com.hansheng.thread.RunnableAndThreadDifference;

/**
 * Created by hansheng on 2016/8/6.
 * wait有出让Object锁的语义, 要想出让锁, 前提是要先获得锁,
 * 所以要先用synchronized获得锁之后才能调用wait. notify原因类似,
 * Object.wait()和notify()不具有原子性语义, 所以必须用synchronized保证线程安全.
 * <p>
 * yield()方法对应了如下操作: 先检测当前是否有相同优先级的线程处于同可运行状态, 如有,
 * 则把 CPU 的占有权交给此线程, 否则继续运行原来的线程. 所以yield()方法称为“退让”,
 * 它把运行机会让给了同等优先级的其他
 */
public class MyThread extends Thread {
    private int ticket = 5;

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                System.out.println("ticket = " + ticket--);
            }
        }
    }
}

class ThreadDemo {
    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
    }
}