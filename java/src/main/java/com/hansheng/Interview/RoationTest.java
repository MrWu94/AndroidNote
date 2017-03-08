package com.hansheng.Interview;

/**
 * Created by mrwu on 2017/2/19.
 * 链接：https://www.nowcoder.com/courses/1/1/4
 * <p>
 * 如果对于一个字符串A，将A的前面任意一部分挪到后边去形成的字符串称为A的旋转词。比如A="12345"
 * ,A的旋转词有"12345","23451","34512","45123"和"51234"。对于两个字符串A和B，请判断A和B是否互为旋转词。
 * 给定两个字符串A和B及他们的长度lena，lenb，请返回一个bool值，代表他们是否互为旋转词
 */

public class RoationTest {

    public static void main(String... args) {

        System.out.println(chkRotation("1234",4,"1234",4));
    }

    public static boolean chkRotation(String A, int lena, String B, int lenb) {
        // write code here
        if(lena!=lenb){
            return false;
        }
        String str=A+B;
        return str.contains(B);
    }
}
