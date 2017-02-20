package com.hansheng.Algorithm;

/**
 * Created by hansheng on 17-2-17.
 * 二分查找
 * http://wiki.jikexueyuan.com/project/for-offer/question-twenty-seven.html
 */

public class algorithm {

    static int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String... args) {

        System.out.println(binarySearch(a, a.length, 2));
    }

    public static int binarySearch(int a[], int len, int key) {
        if (len < 1 || a == null) {
            return -1;
        }
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (key == a[mid])
                return mid;
            else if (key < a[mid])
                high = mid - 1;
            else
                low = mid + 1;

        }

        return -1;

    }
}
