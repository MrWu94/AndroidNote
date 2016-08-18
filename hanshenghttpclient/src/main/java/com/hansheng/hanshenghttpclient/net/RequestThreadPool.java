package com.hansheng.hanshenghttpclient.net;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hansheng on 2016/8/18.
 */
public class RequestThreadPool {

    private static ThreadPoolExecutor pool;

    static void init() {
        HanShengClientConfig config = HanShengHttpClient.config;
        pool = new ThreadPoolExecutor(config.corePoolZie,
                config.maxPoolSize, config.keepAliveTime,
                config.timeUnit, config.blockingQueue);
    }

    public static void execute(final Runnable runnable) {
        if (runnable != null) {
            pool.execute(runnable);
        }
    }

    public static void removeAllTask() {
        pool.getQueue().clear();
    }

    public static boolean removeTaskFromQueue(final Object obj) {
        if (!pool.getQueue().contains(obj)) {
        }
        return false;
    }

    public static BlockingQueue<Runnable> getQueue() {
        return pool.getQueue();
    }

    public static void shutdown() {
        if (pool != null) {
            pool.shutdown();
        }
    }

    public static void shutdownRightnow(){
        if(pool!=null){
            pool.shutdown();
            try {
                pool.awaitTermination(1, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
