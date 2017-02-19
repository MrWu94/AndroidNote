package com.hansheng.Algorithm;

/**
 * Created by mrwu on 2017/2/19.
 */

public class ListReverseList {
    public static void main(String... args) {
        ListNode head = new ListNode();
        head.value = 1;
        head.next = new ListNode();
        head.next.value = 2;
        head.next.next = new ListNode();
        head.next.next.value = 3;
        head.next.next.next = new ListNode();
        head.next.next.next.value = 4;
        head.next.next.next.next = new ListNode();
        head.next.next.next.next.value = 5;
        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.value = 6;
        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.value = 7;
        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.value = 8;
        head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.value = 9;
//        printList(head);
        printList(reversrList(head));

    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value);
            head = head.next;
        }
    }

    public static ListNode reversrList(ListNode head) {
        ListNode root = new ListNode();
        root.next = null;
        ListNode next;
        while (head != null) {
            next = head.next;
//            head.next = root.next;
            root.next = head;
            head = next;
        }

        return root.next;


    }

    public static class ListNode {
        int value;
        ListNode next;
    }
}
