package com.hansheng.sort;

/**
 * Created by hansheng on 17-2-17.
 */

public class bubbleSort {
    public static void main(String... args) {
        int sort[] = {1, 3, 5, 6, 7, 8, 8};
        int temp;
        for (int i = 0; i < sort.length-1; i++) {
            for (int j = 0; j < sort.length-1-i; j++) {
                if (sort[j] > sort[j + 1]) {
                    temp=sort[j];
                    sort[i] = sort[j + 1];
                    sort[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i <= sort.length - 1; i++) {
            System.out.println(sort[i]);
        }
    }
}
