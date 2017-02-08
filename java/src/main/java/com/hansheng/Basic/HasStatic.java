package com.hansheng.Basic;

/**
 * Created by mrwu on 2017/2/8.
 */

public class HasStatic {
    private static int x = 100;

    public static void main(String args[]) {
        HasStatic hs1 = new HasStatic();
        hs1.x++;
        HasStatic hs2 = new HasStatic();
        hs2.x++;
        hs1 = new HasStatic();
        hs1.x++;
        HasStatic.x--;
        System.out.println("x=" + x);
        Boolean flag = false;
        if (flag = true) {
            System.out.println("true");

        } else {
            System.out.println("false");

        }
    }


}
