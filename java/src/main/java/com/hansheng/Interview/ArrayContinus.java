package com.hansheng.Interview;

import java.util.Arrays;

/**
 * Created by hansheng on 17-3-8.
 */

public class ArrayContinus {
    public static void main(String[] args) {
        int[] numbers1 = {1, 3, 2, 5, 4};
        System.out.println(isContinuous(numbers1));
        int[] numbers2 = {1, 3, 2, 6, 4};
        System.out.println(isContinuous(numbers2));
        int[] numbers3 = {0, 3, 2, 6, 4};
        System.out.println(isContinuous(numbers3));
        int[] numbers4 = {0, 3, 1, 6, 4};
        System.out.println(isContinuous(numbers4));
        int[] numbers5 = {1, 3, 0, 5, 0};
        System.out.println(isContinuous(numbers5));
        int[] numbers6 = {1, 3, 0, 7, 0};
        System.out.println(isContinuous(numbers6));
        int[] numbers7 = {1, 0, 0, 5, 0};
        System.out.println(isContinuous(numbers7));
        int[] numbers8 = {1, 0, 0, 7, 0};
        System.out.println(isContinuous(numbers8));
        int[] numbers9 = {3, 0, 0, 0, 0};
        System.out.println(isContinuous(numbers9));
        int[] numbers10 = {0, 0, 0, 0, 0};
        System.out.println(isContinuous(numbers10));
        int[] numbers11 = {1, 0, 0, 1, 0};
        System.out.println(isContinuous(numbers11));
    }

    public static boolean isContinuous(int[] arr) {

        if (arr == null || arr.length != 5) {
            return false;
        }
        Arrays.asList(arr);
        int numberOfZero = 0;
        int numberOfGap = 0;

        for (int i = 0; i < arr.length && arr[i] == 0; i++) {
            numberOfZero++;
        }
        int start = numberOfZero;
        int big = start + 1;
        while (big < arr.length) {
            if (arr[start] == arr[big]) {
                return false;
            }
            numberOfGap += arr[big] - arr[start] - 1;
            start = big;
            big++;
        }
        return numberOfGap <= numberOfZero;


    }
}
