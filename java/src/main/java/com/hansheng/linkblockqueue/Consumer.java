package com.hansheng.linkblockqueue;

import java.util.Random;

/**
 * Created by hansheng on 16-9-27.
 */

public class Consumer implements Runnable {

    @Override
    public void run() {
        while (MarketStorage.isRun_Cousumer) {

            try {
                //随机睡眠
                Thread.sleep(new Random().nextInt(MarketStorage.CONSUMER_THREAD_SLEEP));

                //消费对象
                CommodityObj commodityObj = MarketStorage.blockingQueue.take();
                System.out.println(this + " consumer obj ->" + commodityObj);
                MarketStorage.getConsumerObj_Count.getAndIncrement();//计数器++
                System.out.println("getConsumerObj_Count is :" + MarketStorage.getConsumerObj_Count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}