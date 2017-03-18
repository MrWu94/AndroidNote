package com.hansheng.Finally;

/**
 * Created by mrwu on 2017/3/17.
 */

public class FinalTest {
    public static void main(String[] args) {
        System.out.println(getValue());
    }

    private static int getValue() {
        int i = 1;
        try {
            return i;
        } finally {
            i++;
        }
    }
}
