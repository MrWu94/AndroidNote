package com.hansheng.Interview;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hansheng on 17-2-22.
 */

public class BinaryTreeDemo {
    public static void main(String... args){
        BinaryTreeNode root=new BinaryTreeNode();
        root.value=1;
        root.left=new BinaryTreeNode();
        root.left.value=2;
        root.right=new BinaryTreeNode();
        root.right.value=3;
        root.left.left=new BinaryTreeNode();
        root.left.left.value=4;
        root.left.right=new BinaryTreeNode();
        root.left.right.value=5;
        root.right.left=new BinaryTreeNode();
        root.right.left.value=6;
        root.right.right=new BinaryTreeNode();
        root.right.right.value=7;
        printLineNode(root);
    }

    public static class BinaryTreeNode{
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static void printLineNode(BinaryTreeNode root){

        if (root!=null){
            Queue<BinaryTreeNode> list=new LinkedList<>();
            list.add(root);
            BinaryTreeNode curNode=root;

            while (!list.isEmpty()){
                curNode=list.remove();
                System.out.print(curNode.value);
                if(curNode.left!=null){
                    list.add(curNode.left);
                }
                if (curNode.right!=null){
                    list.add(curNode.right);

                }
            }

        }



    }
}
