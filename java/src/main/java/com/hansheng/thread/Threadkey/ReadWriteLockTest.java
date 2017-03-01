package com.hansheng.Thread.Threadkey;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by hansheng on 16-9-5.
 * 如果说这就是Lock，那么它不能成为同步问题更完美的处理方式，下面要介绍的是读写锁(ReadWriteLock)，
 * 我们会有一种需求，在对数据进行读写的时候，为了保证数据的一致性和完整性，
 * 需要读和写是互斥的，写和写是互斥的，但是读和读是不需要互斥的，这样读和读不互斥性能更高些
 */

public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Data data = new Data();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }).start();
        }
    }
}

class Data {
    private int data;// 共享数据
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void set(int data) {
        rwl.writeLock().lock();// 取到写锁
        try {
            System.out.println(Thread.currentThread().getName() + "准备写入数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);
        } finally {
            rwl.writeLock().unlock();// 释放写锁
        }
    }

    public void get() {
        rwl.readLock().lock();// 取到读锁
        try {
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);
        } finally {
            rwl.readLock().unlock();// 释放读锁
        }
    }
}

