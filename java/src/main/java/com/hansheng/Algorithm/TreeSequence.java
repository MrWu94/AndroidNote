package com.hansheng.Algorithm;



/**
 * Created by mrwu on 2017/3/5.
 */

public class TreeSequence {

    public static boolean verifySequenceOfBST(int[] sequence){

        if(sequence==null||sequence.length<=0){
            return false;
        }

        return verifySequenceBST(sequence,0,sequence.length-1);

    }

    public static boolean verifySequenceBST(int[] sequence,int start,int end){

        if(start>=end){
            return true;
        }

        int index=start;

        while (index<=end-1&&sequence[index]<sequence[end]){
            index++;
        }
        int right=index;

        while (index<=end-1&&sequence[index]>sequence[end]){
            index++;
        }

        if(index<end-1){
            return false;
        }

       index=right;

        return verifySequenceBST(sequence,start,index-1)&&verifySequenceBST(sequence,index-1,end);


    }




    public static void main(String... args){

        //           10
        //         /   \
        //        6     14
        //       /\     /\
        //      4  8  12  16
        int[] data = {4, 8, 6, 12, 16, 14, 10};
        System.out.println("true: " + verifySequenceOfBST(data));
        //           5
        //          / \
        //         4   7
        //            /
        //           6
        int[] data2 = {4, 6, 7, 5};
        System.out.println("true: " + verifySequenceOfBST(data2));
        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        int[] data3 = {1, 2, 3, 4, 5};
        System.out.println("true: " + verifySequenceOfBST(data3));
        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        int[] data4 = {5, 4, 3, 2, 1};
        System.out.println("true: " + verifySequenceOfBST(data4));
        // 树中只有1个结点
        int[] data5 = {5};
        System.out.println("true: " + verifySequenceOfBST(data5));
        int[] data6 = {7, 4, 6, 5};
        System.out.println("false: " + verifySequenceOfBST(data6));
        int[] data7 = {4, 6, 12, 8, 16, 14, 10};
        System.out.println("false: " + verifySequenceOfBST(data7));
    }

}
