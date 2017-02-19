package com.hansheng.Interview;

/**
 * Created by mrwu on 2017/2/19.
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
