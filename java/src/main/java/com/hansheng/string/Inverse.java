package com.hansheng.string;

/**
 * Created by hansheng on 2016/7/19.
 * 实现字符串的反转
 */
public class Inverse {
    public static void main(String... args) {
        System.out.println(inverse("hansheng"));
    }

    private static String inverse(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length/2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = temp;
        }
        return String.copyValueOf(chars);
    }
}
