package com.hansheng.scheduler;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by hansheng on 16-9-21.
 */

public class Scheduler {
    private SchedulerService service;
    private Runnable worker;
    private long period = -1;
    private long delay = 0;
    private ScheduledFuture<?> task;

    void setService(SchedulerService service) {
        this.service = service;
    }

    void setWorker(Runnable worker) {
        this.worker = worker;
    }

    void setPeriod(long period) {
        this.period = period;
    }

    void setDelay(long delay) {
        this.delay = delay;
    }

    public SchedulerService getService() {
        return service;
    }

    public Runnable getWorker() {
        return worker;
    }

    public ScheduledFuture<?> getTask() {
        return task;
    }

    public void cancel() {
        if (this.task != null
                && (!this.task.isCancelled() || !this.task.isDone())) {
            this.task.cancel(true);
        }
    }

    public long getDelay() {
        return delay;
    }

    public long getPeriod() {
        return period;
    }

    public boolean isRunning() {
        return task != null && !task.isCancelled() && !task.isDone();
    }

    public synchronized void start() {
        if (!isRunning()) {
            if (service != null && worker != null) {
                if (this.period > 0) {
                    task = service.getPool().scheduleWithFixedDelay(worker,
                            this.delay, this.period, TimeUnit.MILLISECONDS);
                } else {
                    task = service.getPool().schedule(worker, this.delay,
                            TimeUnit.MILLISECONDS);
                }
            }
        }
    }
}