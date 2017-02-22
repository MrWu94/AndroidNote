package com.hansheng.Interview;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hansheng on 17-2-22.
 */

public class BinaryTreeSubDemo {

    public static void main(String... args) {
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(4);
        root.left.right = new BinaryTree(5);
        root.right.left = new BinaryTree(6);
        root.right.right = new BinaryTree(7);
        findPath(root,7);


    }

    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }


    public static void findPath(BinaryTree root, int expectedSum) {
        List<Integer> list = new LinkedList<>();
        if (root != null) {
            findPath(root, 0, expectedSum, list);
        }
    }

    public static void findPath(BinaryTree root, int cur, int expectedSum, List<Integer> list) {
        if (root != null) {
            cur += root.value;
            list.add(root.value);
            if (cur < expectedSum) {
                findPath(root.left, cur, expectedSum, list);
                findPath(root.right, cur, expectedSum, list);
            } else if (cur == expectedSum) {
                if (root.left == null && root.right == null) {
                    System.out.println(list);
                }
            }
            list.remove(list.size() - 1);
        }


    }
}
