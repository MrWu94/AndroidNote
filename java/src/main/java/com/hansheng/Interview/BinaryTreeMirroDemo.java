package com.hansheng.Interview;

/**
 * Created by hansheng on 17-2-22.
 */

public class BinaryTreeMirroDemo {
    public static void main(String... args) {
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 1;
        root.left = new BinaryTreeNode();
        root.left.value = 2;
        root.right = new BinaryTreeNode();
        root.right.value = 3;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 4;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 5;
        root.right.left = new BinaryTreeNode();
        root.right.left.value = 6;
        root.right.right = new BinaryTreeNode();
        root.right.right.value = 7;
        printTree(root);
        mirro(root);
        printTree(root);

    }

    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static void printTree(BinaryTreeNode root){
        if(root!=null){
            System.out.print(root.value);
            printTree(root.left);
            printTree(root.right);

        }
    }

    public static void mirro(BinaryTreeNode root) {

        if (root != null) {
                BinaryTreeNode tmp = root.left;
                root.left=root.right;
                root.right=tmp;
                mirro(root.left);
                mirro(root.right);
        }
    }
}
