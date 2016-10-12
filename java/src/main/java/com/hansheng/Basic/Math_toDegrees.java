package com.hansheng.Basic;

/**
 * Created by hansheng on 16-10-12.
 * 弧度转换成角度
 * floor 返回不大于的最大整数
 * round 则是4舍5入的计算，入的时候是到大于它的整数
 * round方法，它表示“四舍五入”，算法为Math.floor(x+0.5)，
 * 即将原来的数字加上0.5后再向下取整，所以，Math.round(11.5)的结果为12，Math.round(-11.5)的结果为-11。
 */

public class Math_toDegrees {
    public static void main(String[] args) throws Exception {
        double toDegrees = Math.toDegrees(Math.PI);
        double[] nums = {1.4, 1.5, 1.6, -1.4, -1.5, -1.6};
        for (double num : nums) {
            test(num);
        }
        System.out.println("toDegrees : " + toDegrees);
    }

    private static void test(double num) {
        System.out.println("Math.floor(" + num + ")=" + Math.floor(num));
        System.out.println("Math.round(" + num + ")=" + Math.round(num));
        System.out.println("Math.ceil(" + num + ")=" + Math.ceil(num));
    }
}
