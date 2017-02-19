package com.hansheng.Interview;

/**
 * Created by mrwu on 2017/2/19.
 */

public class ArrayDemo {
    public static void main(String... args) {

        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(find(matrix,8));
    }

    public static boolean find(int matrix[][], int number) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;

        }

        int rows = matrix.length;
        int cols = matrix[0].length; // 数组行的列数
        System.out.println(cols);

        int row = 0;
        int col = cols - 1;
        while (row >= 0 && row < rows && col >= 0 && col < cols) {
            if (matrix[row][col] == number) {
                return true;
            } else if (matrix[row][col] > number) {
                col--;

            } else {
                row++;
            }
        }


        return false;
    }
}
