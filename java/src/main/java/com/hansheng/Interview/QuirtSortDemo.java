package com.hansheng.Interview;

/**
 * Created by hansheng on 17-3-8.
 * http://blog.csdn.net/morewindows/article/details/6684558
 * 挖坑填数+分治法
 * <p>
 * 对挖坑填数进行总结
 * <p>
 * 1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
 * <p>
 * 2．j--由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
 * <p>
 * 3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
 * <p>
 * 4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
 * <p>
 * 照着这个总结很容易实现挖坑填数的代码：
 */

public class QuirtSortDemo {

    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 6, 7, 5, 7, 2, 6};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    public static int adjustArray(int s[], int l, int r) //返回调整后基准数的位置
    {
        int i = l, j = r;
        int x = s[l]; //s[l]即s[i]就是第一个坑
        while (i < j) {
            // 从右向左找小于x的数来填s[i]
            while (i < j && s[j] >= x)
                j--;
            if (i < j) {
                s[i] = s[j]; //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                i++;
            }
            // 从左向右找大于或等于x的数来填s[j]
            while (i < j && s[i] < x)
                i++;
            if (i < j) {
                s[j] = s[i]; //将s[i]填到s[j]中，s[i]就形成了一个新的坑
                j--;
            }
        }
        //退出时，i等于j。将x填到这个坑中。
        s[i] = x;

        return i;
    }

    //再写分治法的代码：
    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int i = adjustArray(arr, l, r);
            quickSort(arr, l, i - 1);
            quickSort(arr, i + 1, r);
        }
    }
}
