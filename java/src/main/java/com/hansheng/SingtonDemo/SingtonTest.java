package com.hansheng.SingtonDemo;

/**
 * Created by mrwu on 2017/2/19.
 * http://blog.csdn.net/ns_code/article/details/17348313
 * Java语言中有一个“先行发生”（happen—before）的规则，它是Java内存模型中定义的两项操作之间的偏序关系，
 * 如果操作A先行发生于操作B，其意思就是说，在发生操作B之前，操作A产生的影响都能被操作B观察到，“影响”包括修改了内存中共享变量的值、
 * 发送了消息、调用了方法等，它与时间上的先后发生基本没有太大关系。
 * DCL即双重检查加锁，关于单例模式的DCL机制
 *   针对延迟加载法的同步实现所产生的性能低的问题，我们可以采用DCL，
 *   即双重检查加锁（Double Check Lock）的方法来避免每次调用getInstance（）方法时都同步
 */

public class SingtonTest {

    private SingtonTest() {
    }

    public static synchronized SingtonTest getInStance() {
        return new SingtonTest();
    }

    private static volatile SingtonTest singtonTest;

    public static synchronized SingtonTest getInstance1() {
        if (singtonTest == null) {
            singtonTest = new SingtonTest();
        }

        return singtonTest;
    }

    //在类加载中实例化
    private static SingtonTest singtonTest1 = new SingtonTest();

    public static SingtonTest getSingtonTest2() {
        return singtonTest1;
    }

    public static SingtonTest getInstance() {


        if (singtonTest == null) {
            synchronized (SingtonTest.class) {
                if (singtonTest == null) {
                    singtonTest = new SingtonTest();
                }
            }

        }
        return singtonTest;
    }


}
