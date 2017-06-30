package com.hansheng.Algorithm;

/**
 * Created by mrwu on 2017/6/30.
 */

public class DynamicPrograming {
    public static void main(String... args) {

        getClimbWays(10);
    }

    public static int getClimbWays(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }
}
