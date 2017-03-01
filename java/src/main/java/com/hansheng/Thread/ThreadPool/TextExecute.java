package com.hansheng.Thread.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hansheng on 16-9-27.
 * 通过Executors的以上四个静态工厂方法获得 ExecutorService实例，而后调用该实例的execute（Runnable command）方法即可。
 * 一旦Runnable任务传递到execute（）方法，该方法便会自动在一个线程上执行
 */

public class TextExecute {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
//      ExecutorService executorService = Executors.newFixedThreadPool(5);
//      ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++){
            executorService.execute(new TestRunnable());
            System.out.println("************* a" + i + " *************");
        }
        executorService.shutdown();
    }
}

class TestRunnable implements Runnable{
    public void run(){
        System.out.println(Thread.currentThread().getName() + "线程被调用了。");
    }
}
