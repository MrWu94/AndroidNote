package com.hansheng.Thread.AtomicInteger;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hansheng on 16-9-19.
 */

public class AtomicTest {
    static long randomTime() {
        return (long) (Math.random() * 1000);
    }

    public static void main(String[] args) {
        // 阻塞队列，能容纳100个文件
        final BlockingQueue<File> queue = new LinkedBlockingQueue<File>(100);
        // 线程池
        final ExecutorService exec = Executors.newFixedThreadPool(5);
        final File root = new File("D:\\ISO");
        // 完成标志
        final File exitFile = new File("");
        // 原子整型，读个数
        // AtomicInteger可以在并发情况下达到原子化更新，避免使用了synchronized，而且性能非常高。
        final AtomicInteger rc = new AtomicInteger();
        // 原子整型，写个数
        final AtomicInteger wc = new AtomicInteger();
        // 读线程
        Runnable read = new Runnable() {
            public void run() {
                scanFile(root);
                scanFile(exitFile);
            }

            public void scanFile(File file) {
                if (file.isDirectory()) {
                    File[] files = file.listFiles(new FileFilter() {
                        public boolean accept(File pathname) {
                            return pathname.isDirectory() || pathname.getPath().endsWith(".iso");
                        }
                    });
                    for (File one : files)
                        scanFile(one);
                } else {
                    try {
                        // 原子整型的incrementAndGet方法，以原子方式将当前值加 1，返回更新的值
                        int index = rc.incrementAndGet();
                        System.out.println("Read0: " + index + " " + file.getPath());
                        // 添加到阻塞队列中
                        queue.put(file);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        // submit方法提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
        exec.submit(read);

        // 四个写线程
        for (int index = 0; index < 4; index++) {
            // write thread
            final int num = index;
            Runnable write = new Runnable() {
                String threadName = "Write" + num;

                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(randomTime());
                            // 原子整型的incrementAndGet方法，以原子方式将当前值加 1，返回更新的值
                            int index = wc.incrementAndGet();
                            // 获取并移除此队列的头部，在元素变得可用之前一直等待（如果有必要）。
                            File file = queue.take();
                            // 队列已经无对象
                            if (file == exitFile) {
                                // 再次添加"标志"，以让其他线程正常退出
                                queue.put(exitFile);
                                break;
                            }
                            System.out.println(threadName + ": " + index + " " + file.getPath());
                        } catch (InterruptedException e) {
                        }
                    }
                }

            };
            exec.submit(write);
        }
        exec.shutdown();
    }
}
