package com.hansheng.Thread.productandConsumer.ProducerConsumerDemo;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hansheng on 17-3-22.
 */
public class ProducerConsumerTest {

    private final static int BUFFER_SIZE = 10;
    private static LinkedList<Object> buffer = new LinkedList<Object>();


    public static void  main (String args[]){
        //创建2个Producer,3个Consumer
        Thread producerA = new Thread(createProducer("producerA"));
        Thread producerB = new Thread(createProducer("producerB"));
        Thread consumerA = new Thread(createConsumer("consumerA"));
        Thread consumerB = new Thread(createConsumer("consumerB"));
        Thread consumerC = new Thread(createConsumer("consumerC"));

        producerA.start();
        producerB.start();
        consumerA.start();
        consumerB.start();
        consumerC.start();
    }

    private static Producer createProducer(String name){
        return new Producer(name);
    }

    private static Consumer createConsumer(String name){
        return new Consumer(name);
    }

    static class Producer extends Thread{
        public Producer(String name){
            this.setName(name);
        }
        private static AtomicInteger i = new AtomicInteger(0);
        @Override
        public void run() {
            while(i.getAndIncrement() < BUFFER_SIZE){
                synchronized (buffer){
                    produce();
                    buffer.notifyAll();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void produce(){
            buffer.add(new Object());
            System.out.println(this.getName() + " produce object. buffer.size = " + buffer.size());
        }
    }

    static class Consumer extends Thread{
        public Consumer(String name){
            this.setName(name);
        }
        private static AtomicInteger i = new AtomicInteger(0);
        @Override
        public void run() {
            while (i.getAndIncrement() < BUFFER_SIZE) {
                synchronized (buffer) {
                    while (buffer.size() == 0) {
                        try {
                            buffer.wait(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer();
                }
            }
        }
        public void consumer(){
            buffer.removeFirst();
            System.out.println(this.getName() + " consume object. buffer.size = " + buffer.size());
        }

    }

}
