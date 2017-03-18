package com.hansheng.Algorithm;

/**
 * Created by hansheng on 17-3-3.
 * 二叉树转换为链表
 */

public class TreeConverter {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    class Converter {
        private ListNode head = new ListNode(-1);
        private ListNode q = head;

        public ListNode treeToList(TreeNode root) {
            // write code here
            if (root != null) {
                treeToList(root.left);
                q.next = new ListNode(root.val);
                q = q.next;
                treeToList(root.right);
            }
            return head.next;
        }
    }
}
