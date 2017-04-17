package com.hansheng.Thread.correspond;

/**
 * Created by hansheng on 17-4-17.
 * 丢失的信号（Missed Signals）
 * notify()和notifyAll()方法不会保存调用它们的方法，因为当这两个方法被调用时，
 * 有可能没有线程处于等待状态。通知信号过后便丢弃了。因此，如果一个线程先于被通知线程调用wait()前调用了notify()，
 * 等待的线程将错过这个信号。这可能是也可能不是个问题。不过，在某些情况下，这可能使等待线程永远在等待，不再醒来，
 * 因为线程错过了唤醒信号。 为了避免丢失信号，必须把它们保存在信号类里。在MyWaitNotify的例子中，通知信号应被存储在
 * MyWaitNotify实例的一个成员变量里。
 */

public class MyWaitNotify2 {

    MonitorObject myMonitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void doWait() {
        synchronized (myMonitorObject) {
            if (!wasSignalled) {
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