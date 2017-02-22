package com.hansheng.Interview;

/**
 * Created by hansheng on 17-2-22.
 */

public class ListNodeDemo {


    public static void main(String... args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);

        head = merge(head, head1);
        printList(head);
    }

    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value);
            head = head.next;
        }
    }

    public static ListNode merge(ListNode head1, ListNode head2) {


        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        ListNode curList = head1;
        if (curList.value < head2.value) {
            curList.next = merge(head1.next, head2);
        } else {
            curList = head2;
            curList.next = merge(head1, head2.next);
        }
        return curList;
    }
}
