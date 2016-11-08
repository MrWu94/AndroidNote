package com.hansheng.block;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hansheng on 16-8-25.
 * 线程同步的方法有什么；锁，synchronized块，信号量
 * 锁的等级：方法锁，对象锁，类锁
 * 生产者消费者模式的几种实现，阻塞队列实现，sync关键字实现，lock实现,reentrantLock等
 *
 */

public class Test {

    public static void main(String... args){
        List<Future<Integer>> list=new ArrayList<>();
        ExecutorService service= Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            list.add(service.submit(new MyTask((int) (Math.random()*100))));
        }
        int sum=1;
        for(Future<Integer> future:list){
            while (!future.isDone()){
                try {
                    sum+=future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(sum);
            }


        }
    }



}
