package com.hansheng.Threadkey;

import java.util.LinkedList;

/**
 * Created by hansheng on 16-9-21.
 * java 线程池与任务队列
 */

public class WorkQueue {
    private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedList queue;

    public WorkQueue(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedList();
        threads = new PoolWorker[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }

    }

    public void execute(Runnable r) {
        synchronized (queue) {
            queue.addLast(r);
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable r;
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    r = (Runnable) queue.removeFirst();
                }
                r.run();

            }
        }
    }

    public static void main(String args[]) {
        WorkQueue wq = new WorkQueue(10);
        MyTask r[] = new MyTask[20];
        for (int i = 0; i < 20; i++) {
            r[i] = new MyTask();
            wq.execute(r[i]);
        }
    }
}

class MyTask implements Runnable {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "execute ok");

    }
}
