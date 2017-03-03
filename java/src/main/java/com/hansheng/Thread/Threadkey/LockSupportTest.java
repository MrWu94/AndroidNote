package com.hansheng.Thread.Threadkey;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by hansheng on 16-9-5.LockSupport
 　　是用来创建锁和其他同步类的基本线程阻塞原语。 LockSupport中的静态方法park()
 和 unpark() 的作用分别是阻塞线程和解除阻塞线程，而不会导致死锁。
 *
 */

public class LockSupportTest {
    static Thread mainThread = null;
    public static void main(String[] args) {
        //获取主线程
        mainThread = Thread.currentThread();
        //新建线程并启动
        MyThread thread1 = new MyThread("thread1");
        thread1.start();
        //模拟线程工作开始
        System.out.println(Thread.currentThread().getName() + "-----》 runs now!");
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "-----》 running step " + i);
            //当前线程睡眠1秒
            sleepOneSecond();
            if(i == 2){
                System.out.println(Thread.currentThread().getName() + "-----》 now pack main thread——————————");
                //让主线程阻塞
                LockSupport.park();
            }
        }
        System.out.println(Thread.currentThread().getName() + "-----》 run over!");

    }

    /**当前线程暂停一秒钟 */
    public static void sleepOneSecond(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        public MyThread(String name){
            super(name);
        }

        @Override
        public void run() {
            synchronized (this) {
                //模拟工作开始
                System.out.println(Thread.currentThread().getName() + "-----》 runs now!");
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "-----》 running step " + i);
                    //当前线程睡眠1秒
                    sleepOneSecond();
                }
                //模拟工作结束
                System.out.println(Thread.currentThread().getName() + "-----》 run over!");

            }
            System.out.println(Thread.currentThread().getName() + "-----》 now unpack main thread———————— ");
            //解除主线程的阻塞
            LockSupport.unpark(mainThread);
        }
    }
}