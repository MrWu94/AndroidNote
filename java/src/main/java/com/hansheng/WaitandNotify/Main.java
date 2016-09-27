package com.hansheng.WaitandNotify;


/**
 * Created by hansheng on 2016/9/27.
 * 在调用wait(), notify()或notifyAll()的时候，必须先获得锁，且状态变量须由该锁保护，
 * 而固有锁对象与固有条件队列对象又是同一个对象。也就是说，
 * 要在某个对象上执行wait，notify，先必须锁定该对象，而对应的状态变量也是由该对象锁保护的。
 */

public class Main {
    public static void main(String[] args) {
        QueueBuffer1 q = new QueueBuffer1();
        new Producer(q);
        new Consumer(q);
        System.out.println("Press Control-C to stop.");
    }
}
 class Consumer implements Runnable {

    private QueueBuffer1 q;

    Consumer(QueueBuffer1 q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (true) {
            q.get();
        }
    }

}
class Producer implements Runnable {

    private QueueBuffer1 q;

    Producer(QueueBuffer1 q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
        }
    }

}
 class QueueBuffer1 {
    int n;
    boolean valueSet = false;

    synchronized int get() {
        if (!valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        if (valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}
