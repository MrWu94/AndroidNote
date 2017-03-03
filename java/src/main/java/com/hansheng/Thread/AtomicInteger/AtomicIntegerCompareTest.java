package com.hansheng.Thread.AtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hansheng on 16-9-19.
 * AtomicInteger，一个提供原子操作的Integer的类。在Java语言中，++i和i++操作并不是线程安全的，
 * 在使用的时候，不可避免的会用到synchronized关键字。而AtomicInteger则通过一种线程安全的加减操作接口。
 * 来看AtomicInteger提供的接口。
 * <p>
 * //获取当前的值
 * <p>
 * public final int get()
 * <p>
 * //取当前的值，并设置新的值
 * <p>
 * public final int getAndSet(int newValue)
 * <p>
 * //获取当前的值，并自增
 * <p>
 * public final int getAndIncrement()
 * <p>
 * //获取当前的值，并自减
 * <p>
 * public final int getAndDecrement()
 * <p>
 * //获取当前的值，并加上预期的值
 * <p>
 * public final int getAndAdd(int delta)
 */

public class AtomicIntegerCompareTest {
    private int value;

    public AtomicIntegerCompareTest(int value) {
        this.value = value;
    }

    public synchronized int increase() {
        return value++;
    }

    public static void main(String args[]) {
        long start = System.currentTimeMillis();

        AtomicIntegerCompareTest test = new AtomicIntegerCompareTest(0);
        for (int i = 0; i < 1000000; i++) {
            test.increase();
        }
        long end = System.currentTimeMillis();
        System.out.println("time elapse:" + (end - start));

        long start1 = System.currentTimeMillis();

        AtomicInteger atomic = new AtomicInteger(0);

        for (int i = 0; i < 1000000; i++) {
            atomic.incrementAndGet();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("time elapse:" + (end1 - start1));


    }
}
