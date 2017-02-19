package com.hansheng.Interview;

/**
 * Created by mrwu on 2017/2/19.
 * 请写出选择排序和插入排序
 */

public class Sort {
    public static void main(String... args) {
        int arr[] = {1, 3, 5, 6, 3, 6, 8};
        int temp;
        for (int i = 0; i < arr.length; i++) {
            temp = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (temp > arr[j]) {
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int a : arr) {
            System.out.println(a);
        }

    }

}
