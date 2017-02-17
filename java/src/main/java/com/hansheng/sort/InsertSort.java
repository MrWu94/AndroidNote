package com.hansheng.sort;

/**
 * Created by hansheng on 17-2-17.
 * 插入排序
 * ：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排
 * 好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
 * 也是排好顺序的。如此反复循环，直到全部排好顺序。
 */

public class InsertSort {

    public static void main(String... args) {
        int sort[] = {1, 3, 5, 6, 7, 8, 8};

        int temp;

        for (int i = 1; i < sort.length; i++) {
            int j = i - 1;
            temp = sort[i];
            for (; j > 0 && temp < sort[j]; j--) {
                sort[j + 1] = sort[j];
            }
            sort[j] = temp;
        }

        for (int i = 0; i < sort.length; i++) {
            System.out.println(sort[i]);
        }
    }
}
