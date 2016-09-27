package com.hansheng.linkblockqueue;

import java.util.Random;

/**
 * Created by hansheng on 16-9-27.
 */
public class Producer implements Runnable {

    @Override
    public void run() {
        while (MarketStorage.isRun_Cousumer) {
            //随机睡眠
            try {
                Thread.sleep(new Random().nextInt(MarketStorage.PRODUCER_THREAD_SLEEP));

                //生产对象
                CommodityObj commodityObj = new CommodityObj();
                MarketStorage.blockingQueue.put(commodityObj);
                System.out.println(this + " producer obj succeed->" + commodityObj);

                MarketStorage.getProducerObj_Count.getAndIncrement();//计数器++
                System.out.println("getProducerObj_Count is :" + MarketStorage.getProducerObj_Count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
