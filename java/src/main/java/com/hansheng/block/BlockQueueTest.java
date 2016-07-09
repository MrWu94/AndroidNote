package com.hansheng.block;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by hansheng on 2016/7/8.
 * 作为BlockingQueue的使用者，我们再也不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给你一手包办了。
 * 　offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,
 *   基于链表的阻塞队列，同ArrayListBlockingQueue类似，其内部也维持着一个数据缓冲队列（该队列由一个链表构成），当生产者往队列中放入一个数据时，
 *   队列会从生产者手中获取数据，并缓存在队列内部，而生产者立即返回；只有当队列缓冲区达到最大值缓存容量时（LinkedBlockingQueue可以通过构造函数指定该值），
 *   才会阻塞生产者队列，直到消费者从队列中消费掉一份数据，生产者线程会被唤醒，反之对于消费者这端的处理也基于同样的原理。
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
