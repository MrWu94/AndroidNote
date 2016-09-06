package com.hansheng.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorDemo {
    private static final int MAX = 10;

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		fixedThredPool(3);
        newCachedThreadPool();
    }

    private static void fixedThredPool(int size) {

        ExecutorService executorService = Executors.newFixedThreadPool(size);

        for (int i = 0; i < MAX; i++) {
            Future<Integer> task = executorService.submit(new Callable<Integer>() {

                public Integer call() throws Exception {
                    // TODO Auto-generated method stub
                    System.out.println("执行线程：" + Thread.currentThread().getName());

                    return fibc(20);
                }
            });
            try {
                System.out.println("第" + i + "次计算结果" + task.get());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private static void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < MAX; i++) {
            executorService.submit(new Runnable() {

                public void run() {
                    // TODO Auto-generated method stub
                    System.out.println("执行线程：" + Thread.currentThread().getName() + ",结果" + fibc(20));
                }
            });
        }
    }

    private static int fibc(int num) {
        // TODO Auto-generated method stub
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fibc(num - 1) + fibc(num - 2);
    }

}
