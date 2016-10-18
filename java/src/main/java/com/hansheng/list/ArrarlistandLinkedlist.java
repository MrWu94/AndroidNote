package com.hansheng.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by hansheng on 16-10-17.
 * 一般大家都知道ArrayList和LinkedList的大致区别：
 * 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
 * 2.对于随机访问get和set，ArrayList优于LinkedList，因为LinkedList要移动指针。
 * 3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
 * ArrayList的内部实现是基于基础的对象数组的
 * LinkedList中的get方法是按照顺序从列表的一端开始检查，直到另外一端。对LinkedList而言，访问列表中的某个指定元素没有更快的方法了。
 * 它们之间最主要的区别在于ArrayList是可改变大小的数组，而LinkedList是双向链接串列(doubly LinkedList)
 * 因为Array是基于索引(index)的数据结构，它使用索引在数组中搜索和读取数据是很快的。Array获取数据的时间复杂度是O(1),
 * 但是要删除数据却是开销很大的，因为这需要重排数组中的所有数据。
 * <p>
 * 2) 相对于ArrayList，LinkedList插入是更快的。因为LinkedList不像ArrayList一样，不需要改变数组的大小，也
 * 不需要在数组装满的时候要将所有的数据重新装入一个新的数组，这是ArrayList最坏的一种情况，时间复杂度是O(n)，而LinkedList
 * 中插入或删除的时间复杂度仅为O(1)。ArrayList在插入数据时还需要更新索引（除了插入数组的尾部）。
 * <p>
 * ArrayList: 内部采用数组存储元素，支持高效随机访问，支持动态调整大小
 * <p>
 * LinkedList: 内部采用链表来存储元素，支持快速插入/删除元素，但不支持高效地随机访问
 */

public class ArrarlistandLinkedlist {

    public static final int N = 50000;

    public static List values;

    static {
        Integer vals[] = new Integer[N];

        Random r = new Random();

        for (int i = 0, currval = 0; i < N; i++) {
            vals[i] = new Integer(currval);
            currval += r.nextInt(100) + 1;
        }

        values = Arrays.asList(vals);
    }

    static long timeList(List lst) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            int index = Collections.binarySearch(lst, values.get(i));
            if (index != i)
                System.out.println("***错误***");
        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String args[]) {
        System.out.println("ArrayList消耗时间：" + timeList(new ArrayList(values)));
        System.out.println("LinkedList消耗时间：" + timeList(new LinkedList(values)));
    }
}
