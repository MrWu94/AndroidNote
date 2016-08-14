package com.hansheng.sort;

/**
 * Created by hansheng on 2016/8/14.
 * 选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,
 * 一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
 */
public class quicksort {
    int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};

    public quicksort() {
        quick(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private void quick(int[] a) {
        if (a.length > 0) {
            quicksort(a, 0, a.length - 1);
        }
    }

    public void quicksort(int[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);
            quicksort(list, low, middle - 1);
            quicksort(list, middle + 1, high);
        }
    }

    private int getMiddle(int[] list, int low, int high) {
        int temp = list[low]; //数组的第一个作为中轴
        while (low < high) {
            while (low < high && list[high] >= temp) {
                high--;
            }
            list[low] = list[high]; //比中轴小的记录移到低端

            while (low < high && list[low] <= temp) {

                low++;

            }

            list[high] = list[low];   //比中轴大的记录移到高端

        }

        list[low] = temp;              //中轴记录到尾

        return low;                   //返回中轴的位置

    }

    public static void main(String... args){
        new quicksort();
    }
}


