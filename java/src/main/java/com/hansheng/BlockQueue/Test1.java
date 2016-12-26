package com.hansheng.BlockQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by hansheng on 16-12-26.
 * 使用阻塞队列代码要简单得多，不需要再单独考虑同步和线程间通信的问题。
 */

public class Test1 {
    private int queueSize = 10;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);

    public static void main(String[] args)  {
        Test1 test = new Test1();
        Producer1 producer = test.new Producer1();
        Consumer1 consumer = test.new Consumer1();

        producer.start();
        consumer.start();
    }

    class Consumer1 extends Thread{

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while(true){
                try {
                    queue.take();
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer1 extends Thread{

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while(true){
                try {
                    queue.put(1);
                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
