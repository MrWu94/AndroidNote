package com.hansheng.Algorithm;

/**
 * Created by mrwu on 2017/2/27.
 */

public class DiduiDemo {
    static int i = 1;
 

    public static void main(String... args) {
        System.out.println(treeDepth());
    }

    public static int treeDepth() {

        if (i > 10) {
            System.out.println("==");
            return  0;
        }
        i++;

        int left = treeDepth();
        return left+1;

    }

}
