package com.hansheng.Daemon;

/**
 * Created by hansheng on 2016/9/27.守护线程与普通线程的唯一区别是：当JVM中所有的线程都是守护线程的时候，JVM就可以退出了；
 * 如果还有一个或以上的非守护线程则不会退出。（以上是针对正常退出，调用System.exit则必定会退出）
 * 前台线程是保证执行完毕的，后台线程还没有执行完毕就退出了。
 * thread.setDaemon(true)必须在thread.start()之前设置
 */
public class Test {
    public static void main(String[] args) {
        Thread t1 = new MyCommon();
        Thread t2 = new Thread(new MyDaemon());
        t2.setDaemon(true);         //设置为守护线程
        t2.start();
        t1.start();
    }
}

class MyCommon extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("线程1第" + i + "次执行！");
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyDaemon implements Runnable {
    public void run() {
        for (long i = 0; i < 9999999L; i++) {
            System.out.println("后台线程第" + i + "次执行！");
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}