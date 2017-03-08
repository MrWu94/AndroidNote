package com.hansheng.Algorithm;

/**
 * Created by hansheng on 17-3-8.
 * https://github.com/GeniusVJR/LearningNotes/blob/master/Part3/DataStructure/%E6%A0%88%E5%92%8C%E9%98%9F%E5%88%97.md
 * 递归和非递归方式实现二叉树先、中、后序遍历
 * <p>
 * 先序遍历顺序为根、左、右，中序遍历顺序为左、根、右，后序遍历是左、右、根。
 */

public class TreeOrderDemo {
    public static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

//        preOrderRecur(root);
//        inOderRecur(root);
        posOrderRecur(root);


    }


    /**
     * 前序遍历
     *
     * @param head
     */
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 中序遍历
     *
     * @param head
     */
    public static void inOderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOderRecur(head.left);
        System.out.print(head.value + " ");
        inOderRecur(head.right);
    }

    /**
     * 后序遍历
     *
     * @param head
     */
    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + "");
    }


}
