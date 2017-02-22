package com.hansheng.Interview;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hansheng on 17-2-22.
 */

public class BinaryTreeLineDemo {
    public static void main(String... args) {
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(4);
        root.left.right = new BinaryTree(5);
        root.right.left = new BinaryTree(6);
        root.right.right = new BinaryTree(7);
        print(root);

    }

    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public static void print(BinaryTree root) {
        if (root == null) {
            return;
        }
        List<BinaryTree> list = new LinkedList<>();
        BinaryTree node;
        // 当前层的结点个数
        int current = 1;
        // 记录下一层的结点个数
        int next = 0;
        list.add(root);
        while (list.size() > 0) {
            node = list.remove(0);
            current--;
            System.out.printf("%-3d", node.value);
            if (node.left != null) {
                list.add(node.left);
                next++;
            }
            if (node.right != null) {
                list.add(node.right);
                next++;
            }
            if (current ==0) {
                System.out.println();
                current = next;
                next = 0;
            }
        }
    }
}
