package com.hansheng.String;

/**
 * Created by hansheng on 17-2-17.
 * 题目一：输入一个英文句子，翻转句子中单词的顺序，但单词内字啊的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 举例说明
 * <p>
 * 例如输入字符串”I am a student. ”，则输出”student. a am I”。
 * <p>
 * 解题思路
 * <p>
 * 第一步翻转句子中所有的字符。比如翻转“I am a student. ”中所有的字符得到”.tneduts a m a I”，
 * 此时不但翻转了句子中单词的顺序，连单词内的字符顺序也被翻转了。
 * 第二步再翻转每个单词中字符的顺序，就得到了”student. a am I”。这正是符合题目要求的输出。
 */

public class StringReverse {


    public static void main(String... args) {

        int[][] matrix = {
                {1, 2, 8, 9,3},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15},
                {6, 8, 11, 15}
        };
        System.out.println(matrix[0].length);
        System.out.println(matrix[1].length);

        String str="hhh";
        str.charAt(1);
        char cha[]=str.toCharArray();


        System.out.println(reverseSentence(new char[]{'a', 's', 'a', 'c', ' ', 'a', 'r'}));

    }

    /**
     * 将data中start到end之间的数字反转
     *
     * @param data
     * @param start
     * @param end
     */
    public static void reverse(char[] data, int start, int end) {
        if (data == null || data.length < 1 || start < 0 || end > data.length || start > end) {
            return;
        }
        while (start < end) {
            char tmp = data[start];
            data[start] = data[end];
            data[end] = tmp;
            start++;
            end--;
        }
    }

    /**
     * 题目一：输入一个英文句子，翻转句子中单词的顺序，但单词内字啊的顺序不变。
     * 为简单起见，标点符号和普通字母一样处理。
     *
     * @param data
     * @return
     */
    public static char[] reverseSentence(char[] data) {
        if (data == null || data.length < 1) {
            return data;
        }
        reverse(data, 0, data.length - 1);
        int start = 0;
        int end = 0;
        while (start < data.length) {
            if (data[start] == ' ') {
                start++;
                end++;
            } else if (end == data.length || data[end] == ' ') {
                reverse(data, start, end - 1);
                end++;
                start = end;
            } else {
                end++;
            }
        }
        return data;
    }
}
