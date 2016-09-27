package com.hansheng.linkblockqueue;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hansheng on 16-9-27.
 */
public class MarketStorage {

    //生产者线程池
    protected static final ExecutorService EXECUTOR_SERVICE_PRODUCER
            = Executors.newFixedThreadPool(10);
    //启动生产者线程数量
    protected static final int PRODUCER_THREAD_NUM = 2;
    //生产者线程睡眠随机最大时间
    protected static final int PRODUCER_THREAD_SLEEP = 200;
    //生产者生成对象次数
    protected static AtomicInteger getProducerObj_Count = new AtomicInteger(0);
    //是否生产
    protected static boolean isRun_Producer = true;


    //消费者线程池
    protected static final ExecutorService EXECUTOR_SERVICE_CONSUMER
            = Executors.newFixedThreadPool(10);
    //启动消费者线程数量
    protected static final int CONSUMER_THREAD_NUM = 20;
    //消费者线程睡眠随机最大时间
    protected static final int CONSUMER_THREAD_SLEEP = 1000;
    //消费者消费对象次数
    protected static AtomicInteger getConsumerObj_Count = new AtomicInteger(0);
    //是否消费
    protected static boolean isRun_Cousumer = true;

    //市场仓库-存储数据的队列 默认仓库容量大小100
    /**
     * @see com.java.queue.LinkedBlockingQueue_#linkedBlockingQueue2Void()
     */
    protected static LinkedBlockingQueue<CommodityObj> blockingQueue
            = new LinkedBlockingQueue<CommodityObj>(100);

    /**
     * 生成生产者线程
     */
    private static void runProducer() {
        for (int i = 0; i < PRODUCER_THREAD_NUM; i++) {
            EXECUTOR_SERVICE_PRODUCER.submit(new Producer());
        }
    }

    /**
     * 生成消费者线程生成
     */
    private static void runConsumer() {
        for (int i = 0; i < CONSUMER_THREAD_NUM; i++) {
            Thread thread = new Thread(new Consumer());
            EXECUTOR_SERVICE_CONSUMER.submit(thread);
        }
    }

    /**
     * 停止线程生产与消费
     * 关闭线程池
     */
    private static void shumdown() {
        if (!EXECUTOR_SERVICE_PRODUCER.isShutdown()) {
            isRun_Producer = false;
            EXECUTOR_SERVICE_PRODUCER.shutdown();
        }
        if (!EXECUTOR_SERVICE_CONSUMER.isShutdown()) {
            isRun_Cousumer = false;
            EXECUTOR_SERVICE_CONSUMER.shutdown();
        }
    }


    public static void main(String[] args) {
        runConsumer();
        runProducer();

        /**
         * 1 min 后停止执行
         */
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                shumdown();
                System.out.println("~~~~~~~~~~~~ shumdown done ~~~~~~~~~~~~~~");
            }
        }, 1000 * 60L);
    }
}