package com.hansheng.Algorithm;

/**
 * Created by mrwu on 2017/2/26.
 */

public class TreeDepthDemo {

    /**
     * 二叉树的树结点
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }


    public static void main(String... args){
        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11

        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 8;
        root.left = new BinaryTreeNode();
        root.left.value = 6;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 5;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 10;
        root.right.left = new BinaryTreeNode();
        root.right.left.value = 9;
        root.right.right = new BinaryTreeNode();
        root.right.right.value = 11;

       System.out.println(treeDepth(root));
}

    public static int treeDepth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth(root.left);
//        System.out.println("left="+left);
        int right = treeDepth(root.right);
        return left >=right ? (left + 1) : (right + 1);

    }
}
