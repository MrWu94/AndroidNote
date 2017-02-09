package com.hansheng.Basic;

/**
 * Created by mrwu on 2017/2/9.
 * count是静态变量，为所有对象所共享，因此不管a.increment()还是b.increment()都会使count持续增加。
 * increment()方法返回当前count值，然后count增加1
 * antoherIncrement()方法让count增加1，然后返回count值
 * 第一次a.increment()返回值为0，此时count值为1
 * 第二次a.anotherIncrement()先让count+1再返回，返回值为2
 * 第三次b.increment()先返回count当前值2，然后count+1
 */

public class StaticTest {
    public static void main(String[] args) {
        Counter a = new Counter();
        System.out.println(a.increment());
        System.out.println(a.anotherIncrement());
        Counter b = new Counter();
        System.out.println(b.increment());
    }
}

class Counter {
    private static int count = 0;

    public int increment() {
        return count++;
    }

    public int anotherIncrement() {
        return ++count;
    }
}
