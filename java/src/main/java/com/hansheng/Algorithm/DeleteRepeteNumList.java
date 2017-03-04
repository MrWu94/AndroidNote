package com.hansheng.Algorithm;

import java.util.Stack;

/**
 * Created by hansheng on 17-3-4.
 */

public class DeleteRepeteNumList {

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

    }

    public static ListNode removeVal(ListNode node, int num) {
        Stack<ListNode> stack = new Stack<>();

        while (node != null) {
            if (node.val != num) {
                stack.push(node);
            }
            node = node.next;
        }

        while (!stack.isEmpty()) {
            stack.peek().next = node;
            node = stack.pop();

        }
        return node;
    }

    public static void printList(ListNode root) {
        while (root != null) {
            System.out.println(root.val);
            root = root.next;
        }
    }

    public static void main(String... args) {

        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next= new ListNode(4);
        root.next.next.next.next.next= new ListNode(4);
        root = removeVal(root, 4);
        printList(root);
    }

}
