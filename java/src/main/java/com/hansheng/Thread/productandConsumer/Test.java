package com.hansheng.Thread.productandConsumer;


import com.hansheng.Thread.productandConsumer.BlockQueue.Storage;

/**
 * Created by hansheng on 17-3-22.
 * 同步问题核心在于：如何保证同一资源被多个线程并发访问时的完整性。常用的同步方法是采用信号或加锁机制，保证资源在任意时刻至多被一个线程访问。
 * 该问题的关键就是 要保证生产者不会在缓冲区满时加入数据，消费者也不会在缓冲区中空时消耗数据。
 * <p>
 * 要解决该问题，就必须 让生产者在缓冲区满时休眠（要么干脆就放弃数据），等到下次消费者消耗缓冲区中的数据的时候，
 * 生产者才能被唤醒，开始往缓冲区添加数据。
 * 同样，也可以让 消费者在缓冲区空时进入休眠，等到生产者往缓冲区添加数据之后，再唤醒消费者 。
 */

public class Test {
    public static void main(String[] args) {
        // 仓库对象
        Storage storage = new Storage();

        // 生产者对象
        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);
        Producer p6 = new Producer(storage);
        Producer p7 = new Producer(storage);

        // 消费者对象
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(80);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        // 线程开始执行
        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}
