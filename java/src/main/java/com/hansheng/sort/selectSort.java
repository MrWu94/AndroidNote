package com.hansheng.sort;

/**
 * Created by hansheng on 17-2-17.
 * 选择排序
 * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
 * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
 */

public class selectSort {

    public static void main(String... args) {
        int sort[] = {1, 3, 5, 6, 7, 8, 8};
        int temp;

        for (int i = 0; i <= sort.length - 1; i++) {
            temp = sort[i];
            for (int j = i + 1; j <= sort.length - 1; j++) {
                if (sort[i] > sort[j]) {
                    sort[i] = sort[j];
                    sort[j] = temp;
                }

            }
        }
        for (int i = 0; i <=sort.length - 1; i++) {
            System.out.println(sort[i]);
        }


    }
}
