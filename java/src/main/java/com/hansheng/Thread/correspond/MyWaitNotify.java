package com.hansheng.Thread.correspond;

/**
 * Created by hansheng on 17-4-17.
 * 一个线程一旦调用了任意对象的wait()方法，就会变为非运行状态，直到另一个线程调用了同一个对象的notify()方法。
 * 为了调用wait()或者notify()，线程必须先获得那个对象的锁。
 * 也就是说，线程必须在同步块里调用wait()或者notify()。以下是MySingal的修改版本——使用了wait()和notify()的MyWaitNotify：
 * <p>
 * 等待线程将调用doWait()，而唤醒线程将调用doNotify()。当一个线程调用一个对象的notify()方法，正在等待该对象的所有线程中将有
 * 一个线程被唤醒并允许执行（校注：这个将被唤醒的线程是随机的，不可以指定唤醒哪个线程）。同时也提供了一个notifyAll()方法来唤醒
 * 正在等待一个给定对象的所有线程。如你所见，不管是等待线程还是唤醒线程都在同步块里调用wait()和notify()。这是强制性的！
 * 一个线程如果没有持有对象锁，将不能调用wait()，notify()或者notifyAll()。否则，会抛出IllegalMonitorStateException异常。
 */

public class MyWaitNotify {
    MonitorObject myMonitorObject = new MonitorObject();

    public void doWait() {
        synchronized (myMonitorObject) {
            try {
                myMonitorObject.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public void doNotify() {
        synchronized (myMonitorObject) {
            myMonitorObject.notify();
        }
    }
}
