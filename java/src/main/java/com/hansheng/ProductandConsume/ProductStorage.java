package com.hansheng.ProductandConsume;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hansheng on 16-9-27.
 * 生产者消费者模式的原理描述：
 * （1）生产者仅仅在仓储未满时候生产，仓满则停止生产。
 * （2）消费者仅仅在仓储有产品时候才能消费，仓空则等待。
 * （3）当消费者发现仓储没产品可消费时候会通知生产者生产。
 * （4）生产者在生产出可消费产品时候，应该通知等待的消费者去消费。
 */

public class ProductStorage {
    /**
     * 最大库存量
     */
    public static final int Maximum = 100;

    /**
     * 当前库存量
     */
    public static int Currentimum = 50;

    /**
     * 库存管理实例
     */
    private static ProductStorage instance;

    private ProductStorage() {
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static ProductStorage getInstance() {
        if (instance == null) {
            instance = new ProductStorage();
        }
        return instance;
    }

    /**
     * 生产产品
     */
    public synchronized void product() {
        while (Currentimum >= Maximum / 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Currentimum++;
        System.out.println("当前线程：" + Thread.currentThread().getName() + "--生产者生产了一个商品，当前库存量：" + Currentimum);
        notifyAll();
    }

    /**
     * 消费产品
     */
    public synchronized void consume() {
        while (Currentimum <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Currentimum--;
        System.out.println("当前线程：" + Thread.currentThread().getName() + "--消费者消费了一个商品，当前库存量：" + Currentimum);
        notifyAll();
    }
}

/**
 * 商品生产者模型
 */
class Producter implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ProductStorage.getInstance().product();
        }
    }
}

class Consumer implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ProductStorage.getInstance().consume();
        }

    }
}

class TestMain {

    public static void main(String[] args) {
        startProductThread();
        startConsumThread();
    }

    /**
     * 开启生产者线程
     */
    public static void startProductThread() {
        System.out.println("--生产者线程执行开始--");
        int pThreadSize = 10;
        ExecutorService pool = Executors.newFixedThreadPool(pThreadSize);
        for (int i = 0; i < pThreadSize; i++) {
            Producter productThread = new Producter();
            Thread thread = new Thread(productThread);
            pool.execute(thread);
        }
        System.out.println("--生产者线程执行结束--");
    }

    /**
     * 开启消费者线程
     */
    public static void startConsumThread() {
        System.out.println("--消费者线程执行开始--");
        int pThreadSize = 10;
        ExecutorService pool = Executors.newFixedThreadPool(pThreadSize);
        for (int i = 0; i < pThreadSize; i++) {
            Consumer consumeThread = new Consumer();
            Thread thread = new Thread(consumeThread);
            pool.execute(thread);
        }
        System.out.println("--消费者线程执行结束--");
    }
}
