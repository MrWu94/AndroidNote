package com.hansheng.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hansheng on 2016/7/8.
 * put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续.
 */
public class ArrayBlockQueueTest {

    public static void main(String... args){
        final BlockingQueue queue=new ArrayBlockingQueue(3);
        for(int i=0;i<2;i++){
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep((long) (Math.random()*1000));
                            System.out.println(Thread.currentThread().getName()+"准备放数据");
                            queue.put(1);
                            System.out.println(Thread.currentThread().getName()+"已经放了数据,队列目前有"+queue.size()+"个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+"准备取数据");
                        queue.take();
                        System.out.println(Thread.currentThread().getName()+"已经取走数据,队列目前有"+queue.size()+",个数据");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
