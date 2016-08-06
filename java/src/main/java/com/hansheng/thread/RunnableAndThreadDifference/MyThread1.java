package com.hansheng.thread.RunnableAndThreadDifference;

/**
 * Created by hansheng on 2016/8/6.
 * 1、可以避免由于Java的单继承特性而带来的局限；
 * <p/>
 * 2、增强程序的健壮性，代码能够被多个线程共享，代码与数据是独立的；
 * <p/>
 * 3、适合多个相同程序代码的线程区处理同一资源的情况。
 */
class MyThread1 implements Runnable {
    private int ticket = 5;

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                System.out.println("ticket = " + ticket--);
            }
        }
    }
}

class RunnableDemo {
    public static void main(String[] args) {
        MyThread my = new MyThread();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();
    }
}
