package com.hansheng.thread.share;

/**
 * Created by hansheng on 2016/7/29.
 * 是某个线程内如何共享数据，保证各个线程的数据不交叉；一是多个线程间如何共享数据，保证数据的一致性。
 * 线程范围内共享数据
 * ThreadLocal类
 * 多线程访问共享数据
 */
public class MutilThread {
    /**
     * @param args
     */
    public static void main(String[] args) {
        final MyData data = new MyData();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                public void run() {
                    data.add();

                }

            }).start();
            new Thread(new Runnable() {

                public void run() {
                    data.dec();

                }

            }).start();
        }
    }
}
