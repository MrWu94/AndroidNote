package com.hansheng.block;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by hansheng on 2016/7/8.
 */
public class Consumer implements Runnable {
    private BlockingQueue<String> queue;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;


    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        System.out.println("?????????????");
        Random r = new Random();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("?????????????????");
            try {
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (null != data) {
                    System.out.println("???????" + data);
                    System.out.println("????????????" + data);
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                } else {
                    isRunning = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

                Thread.currentThread().interrupt();
            } finally {
                System.out.println("????????????");
            }
        }

    }
}
