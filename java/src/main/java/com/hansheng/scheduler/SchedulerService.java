package com.hansheng.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sun.misc.Service;

/**
 * Created by hansheng on 16-9-21.
 */

public class SchedulerService {
    private static final int DEFAULT_POOL_SIZE = 2 * Runtime.getRuntime().availableProcessors();

    private int poolSize;
    private ScheduledExecutorService pool;

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        if (poolSize < 0) {
            poolSize = DEFAULT_POOL_SIZE;
        }
        this.poolSize = poolSize;
    }

    protected void initService() {
        pool = Executors.newScheduledThreadPool(poolSize);
    }

    protected void startService() {
        //
    }

    protected void stopService() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                }
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    ScheduledExecutorService getPool() {
        return pool;
    }

    public Scheduler scheduleWithFixedDelay(Runnable task, long delay, long period, TimeUnit util) {
        Scheduler scheduler = new Scheduler();
        scheduler.setDelay(delay);
        scheduler.setPeriod(period);
        scheduler.setWorker(task);
        scheduler.setService(this);
        return scheduler;
    }

    public Scheduler schedule(Runnable task, long delay, TimeUnit util) {
        Scheduler scheduler = new Scheduler();
        scheduler.setDelay(delay);
        scheduler.setPeriod(-1);
        scheduler.setWorker(task);
        scheduler.setService(this);
        return scheduler;
    }
}