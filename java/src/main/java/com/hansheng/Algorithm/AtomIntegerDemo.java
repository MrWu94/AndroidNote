package com.hansheng.Algorithm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hansheng on 17-2-20.
 * 线程同步问题：有3个线程abc，循环输出十次abc。
 */

public class AtomIntegerDemo {
    private static  AtomicInteger sycValue = new AtomicInteger(0);
    private static final int MAX_SYC_VALUE = 3 * 10;

    public static void main(String... args){
        new Thread(new RunnableA()).start();
        new Thread(new RunnableB()).start();
        new Thread(new RunnableC()).start();
    }

    private static  class RunnableA implements Runnable {
        public void run() {
            while (sycValue.get() < MAX_SYC_VALUE) {
                if (sycValue.get() % 3 == 0) {
                    System.out.println(String.format("第%d遍", sycValue.get() / 3 + 1));
                    System.out.println("A");
                    sycValue.getAndIncrement();
                }
            }
        }
    }

    private static class RunnableB implements Runnable {
        public void run() {
            while (sycValue.get() < MAX_SYC_VALUE) {
                if (sycValue.get() % 3 == 1) {
                    System.out.println("B");
                    sycValue.getAndIncrement();
                }
            }
        }
    }

    private static class RunnableC implements Runnable {
        public void run() {
            while (sycValue.get() < MAX_SYC_VALUE) {
                if (sycValue.get() % 3 == 2) {
                    System.out.println("C");
                    System.out.println();
                    sycValue.getAndIncrement();
                }
            }
        }
    }
}
