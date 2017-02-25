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
        printList(reverseList2(head));

    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value);
            head = head.next;
        }
    }

    /**
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     * 【书本上的方法，不使用逻辑头结点】
     *
     * @param head 链表的头结点
     * @return 反转后的链表的头结点
     */
    public static ListNode reverseList2(ListNode head) {
        // 用于记录反转后的链表的头结点
        ListNode reverseHead = null;
        // 用于记录当前处理的结点的
        ListNode curr = head;
        // 用于记录当前结点的前驱结点
        // 前驱结点开始为null，因为了是反转后的最后一个结点的下一个结点，即null
        ListNode prev = null;
        // 当前结点的下一个结点
        ListNode next = null;
        // 对链表进行尾插法操作
        while (curr != null) {
            // 记录当前处理的结点，最后一个记录的结点就是反转后的头结点
            reverseHead = curr;
            // 记录当然前下一个结点
            if (next == null) {
                reverseHead = curr;
            }
            // 当前结点的下一个结点指向前驱结点，这样当前结点就插入到了反转链表的头部
            curr.next = prev;
            // 记录当前结点为前驱结点
            prev = curr;
            // 当前结点点移动到下一个结点
            curr = next;
        }
        // 返回转后的头结点
        return reverseHead;
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
