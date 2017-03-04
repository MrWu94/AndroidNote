package com.hansheng.Algorithm;

import java.util.Stack;

/**
 * Created by hansheng on 17-3-4.
 */

public class ListPalindromel {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static boolean isPalindromel(ListNode head) {

        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;

        }
        while (head != null) {
            if (head.val == stack.pop().val) {
                head = head.next;
            } else {
                return false;
            }

        }
        return true;
    }

    public static void main(String... args) {

        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(4);
        root.next.next.next.next.next = new ListNode(4);
        System.out.println(isPalindromel(root));
    }
}
