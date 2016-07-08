package com.hansheng.block;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by hansheng on 2016/7/8.
 */
public class BlockQueueTest {

    public static void main(String... args) throws InterruptedException {
        // 声明一个容量为10的缓存队列
        BlockingQueue<String> blockingDeque = new LinkedBlockingQueue<String>(10);

        Producer producer=new Producer(blockingDeque);
        Producer producer1=new Producer(blockingDeque);
        Producer producer2=new Producer(blockingDeque);
        Consumer consumer=new Consumer(blockingDeque);

        ExecutorService service= Executors.newCachedThreadPool();

        service.execute(producer);
        service.execute(producer1);
        service.execute(producer2);
        service.execute(consumer);

        Thread.sleep(10*1000);
        producer.stop();
        producer1.stop();
        producer2.stop();

        Thread.sleep(2000);

        service.shutdown();

    }
}
