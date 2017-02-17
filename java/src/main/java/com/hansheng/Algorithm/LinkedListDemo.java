package com.hansheng.Algorithm;

import java.util.LinkedList;

/**
 * Created by hansheng on 17-2-17.
 */

public class LinkedListDemo {

    public static void main(String[] args) {

        // create a LinkedList
        LinkedList list = new LinkedList();

        // add some elements
        list.add("Hello");
        list.add(2);
        list.add("Chocolate");
        list.add("10");

        // print the list
        System.out.println("LinkedList:" + list);

        // retrieve and remove the head of the list
        System.out.println("Head element of the list:" + list.poll());

        // print the list
        System.out.println("LinkedList:" + list);
    }
}