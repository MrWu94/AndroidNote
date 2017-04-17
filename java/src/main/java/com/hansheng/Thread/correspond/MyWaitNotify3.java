package com.hansheng.Thread.correspond;

/**
 * Created by hansheng on 17-4-17.
 * <p>
 * 自旋锁:假唤醒
 * <p>
 * 由于莫名其妙的原因，线程有可能在没有调用过notify()和notifyAll()的情况下醒来。这就是所谓的假唤醒（spurious wakeups）。
 * 无端端地醒过来了。
 * <p>
 * 如果在MyWaitNotify2的doWait()方法里发生了假唤醒，等待线程即使没有收到正确的信号，也能够执行后续的操作。
 * 这可能导致你的应用程序出现严重问题。
 * <p>
 * 为了防止假唤醒，保存信号的成员变量将在一个while循环里接受检查，而不是在if表达式里。这样的一个while循环叫做自旋锁
 * （校注：这种做法要慎重，目前的JVM实现自旋会消耗CPU，
 * 如果长时间不调用doNotify方法，doWait方法会一直自旋，CPU会消耗太大）。
 */
public class MyWaitNotify3 {

    MonitorObject myMonitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void doWait() {
        synchronized (myMonitorObject) {
            while (!wasSignalled) {
                try {
                    myMonitorObject.wait();
                } catch (InterruptedException e) {
                }
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify() {
        synchronized (myMonitorObject) {
            wasSignalled = true;
            myMonitorObject.notify();
        }
    }
}