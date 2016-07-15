package com.hansheng.thread.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by hansheng on 2016/7/15.
 * FutureTask可以返回执行完毕的数据，并且FutureTask的get方法支持阻塞这两个特性，
 * 我们可以用来预先加载一些可能用到资源，然后要用的时候，调用get方法获取（如果资源加载完，直接返回；否则继续等待其加载完成）。
 *
 */
public class PreLoaderUseFutureTask {

    private final FutureTask<String> futureTask=new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            Thread.sleep(3000);
            return "加载资源需要3秒";
        }
    });
    public Thread thread=new Thread(futureTask);

    public void start(){
        thread.start();
    }

    public String getRes(){
        try {
            return futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {

        PreLoaderUseFutureTask task=new PreLoaderUseFutureTask();

        task.start();
        Thread.sleep(2000);

        /**
         * 获取资源
         */
        System.out.println(System.currentTimeMillis() + "：开始加载资源");
        String res = task.getRes();
        System.out.println(res);
        System.out.println(System.currentTimeMillis() + "：加载资源结束");
    }



}
