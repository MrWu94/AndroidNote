package com.hansheng.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * synchronized与可见性
 * synchronized关键字不仅能够保证操作的原子性, 也能保证变量的可见性. JVM规范规定, 如果线程A和线程B通过同一把锁进行同步,
 * 那么线程A在同步代码块中所做的修改对于线程B是可见的.
 * volatile与可见性
 * java的同步机制除了synchronized之外, 还有volatile.
 * 如果使用volatile关键字修饰一个变量, 该变量就被声明为"易变的". JVM规范规定了任何一个线程修改了volatile
 * 变量的值都需要立即将新值更新到主内存中, 任何线程任何时候使用到volatile变量时都需要重新获取主内存的变量值,
 * 而且volatile关键字隐含禁止进行指令重排序优化的语义. 以上的规范保证了volatile变量的线程可见性.
 * <p>
 * synchronized和volatile比较
 * volatile不需要加锁，比synchronized更轻量级，不会阻塞线程，效率更高
 * 从内存可见性角度讲，volatile读相当于加锁，volatile写相当于解锁
 * synchronized技能保证可见性，又能保证原子性，而volatile只能保证可见性，不能保证原子性。
 * 如果能用volatile解决问题，还是应尽量使用volatile，因为它的效率更高
 */


public class FutureDemo {

    static ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        futureWithRunnable();
        futureWithCallable();
        futureTask();

    }

    private static void futureWithRunnable() {

        Future<?> result = mExecutor.submit(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                fibc(20);
            }

        });
        try {
            System.out.println("future result from runnable:" + result.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void futureWithCallable() {
        Future<Integer> result2 = mExecutor.submit(new Callable<Integer>() {

            public Integer call() throws Exception {
                // TODO Auto-generated method stub
                return fibc(20);
            }

        });
        try {
            System.out.println("future result from callable:" + result2.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void futureTask() {

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {

            public Integer call() throws Exception {
                // TODO Auto-generated method stub
                return fibc(20);
            }
        });

        mExecutor.submit(futureTask);
        try {
            System.out.println("suture result from futureTask" + futureTask.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
