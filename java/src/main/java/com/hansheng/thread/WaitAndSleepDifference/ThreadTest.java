package com.hansheng.Thread.WaitAndSleepDifference;

/**
 * Created by hansheng on 2016/8/6.
 * sleep()睡眠时，保持对象锁，仍然占有该锁；
 * 而wait()睡眠时，释放对象锁。
 * 但是wait()和sleep()都可以通过interrupt()方法打断线程的暂停状态，
 * 从而使线程立刻抛出InterruptedException（但不建议使用该方法）。
 * ，main()方法中实例化ThreadTest并启动该线程，然后调用该线程的一个方法（secondMethod()），
 * 因为在主线程中调用方法，所以调用的普通方法secondMethod()）会先被执行（但并不是普通方法执行完毕
 * 该对象的线程方法才执行，普通方法执行过程中，该线程的方法也会被执行，他们是交替执行的，只是在主线程
 * 普通方法会先被执行而已），所以程序运行时会先执行secondMethod()，而secondMethod()方法代码片段
 * 中有synchronized block，因此secondMethod方法被执行后，该方法会占有该对象机锁导致该对象的线
 * 程方法一直处于阻塞状态，不能执行，直到secondeMethod释放锁；使用Thread.sleep(2000)方法时，因为
 * sleep在阻塞线程的同时，并持有该对象锁，所以该对象的其他同步线程（secondMethod()）无法执行，直到
 * synchronized block执行完毕（sleep休眠完毕），secondMethod()方法才可以执行，因此输出结果为number*200+100；
 * 使用this.wait(2000)方法时，secondMethod()方法被执行后也锁定了该对象的机锁，执行到this.wait(2000)时，
 * 该方法会休眠2S并释当前持有的锁，此时该线程的同步方法会被执行（因为secondMethod持有的锁，已经被wait()所释放），
 * 因此输出的结果为：number+100;
 *
 */
public class ThreadTest implements Runnable {
    int number = 10;

    public void firstMethod() throws Exception {
        synchronized (this) {
            number += 100;
            System.out.println(number);
        }
    }

    public void secondMethod() throws Exception {
        synchronized (this) {
            /**
             * (休息2S,阻塞线程)
             * 以验证当前线程对象的机锁被占用时,
             * 是否被可以访问其他同步代码块
             */
//            Thread.sleep(2000);
            this.wait(2000);
            number *= 200;
        }
    }

    @Override
    public void run() {
        try {
            firstMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.start();
        threadTest.secondMethod();
    }
}