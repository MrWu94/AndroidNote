package com.hansheng.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Executor接口中之定义了一个方法execute（Runnable command），该方法接收一个Runable实例，它用来执行一个任务，
 * 任务即一个实现了Runnable接口的类。ExecutorService接口继承自Executor接口，它提供了更丰富的实现多线程的方法，
 * 比如，ExecutorService提供了关闭自己的方法，以及可为跟踪一个或多个异步任务执行状况而生成 Future 的方法。 可
 * 以调用ExecutorService的shutdown（）方法来平滑地关闭 ExecutorService，调用该方法后，将导致ExecutorService
 * 停止接受任何新的任务且等待已经提交的任务执行完成(已经提交的任务会分两类：一类是已经在执行的，另一类是还没有开始执行的)，
 * 当所有已经提交的任务执行完毕后将会关闭ExecutorService。因此我们一般用该接口来实现和管理多线程。
 */

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
