package com.hansheng.Algorithm;

import java.util.Stack;

/**
 * Created by hansheng on 17-3-3.
 */
class PlusList {
    public ListNode plusAB(ListNode a, ListNode b) {
        // write code here
        if (a == null || b == null) {
            return null;
        }
        Stack<ListNode> list = new Stack<>();
        while (b != null) {
            list.push(b);
            b = b.next;
        }

        ListNode tmp=null;
        tmp=list.pop();
        if(!list.isEmpty()){
            tmp.next=list.pop();

        }


        ListNode node = new ListNode(-1);
        ListNode q = node;
        while (a != null) {
            if (tmp!= null) {
                q.val = a.val + tmp.val;
            } else {
                q.val = a.val;
                tmp=tmp.next;
            }

            q = q.next;
            a = a.next;

        }
        return node.next;

    }


    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}