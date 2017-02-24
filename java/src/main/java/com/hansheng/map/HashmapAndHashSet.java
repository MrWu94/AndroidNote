package com.hansheng.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hansheng on 16-10-17.
 * 什么是HashSet
 * <p>
 * HashSet实现了Set接口，它不允许集合中有重复的值，当我们提到HashSet时，第一件事情就是在将对象存储在HashSet之前，
 * 要先确保对象重写equals()和hashCode()方法，这样才能比较对象的值是否相等，以确保set中没有储存相等的对象。如果我们没有重写这两个方法，
 * 将会使用这个方法的默认实现。
 * <p>
 * public boolean add(Object o)方法用来在Set中添加元素，当元素值重复时则会立即返回false，如果成功添加的话会返回true。
 * <p>
 * 什么是HashMap
 * <p>
 * HashMap实现了Map接口，Map接口对键值对进行映射。Map中不允许重复的键。Map接口有两个基本的实现，HashMap和TreeMap。
 * TreeMap保存了对象的排列次序，而HashMap则不能。HashMap允许键和值为null。HashMap是非synchronized的，
 * 但collection框架提供方法能保证HashMap synchronized，这样多个线程同时访问HashMap时，能保证只有一个线程更改Map。
 * <p>
 * HashMap是一种<key,value>的存储结构，能够快速将key的数据put方式存储起来，然后很快的通过get取出来”，然后说“HashMap不是线程安全的，
 * HashTable是线程安全的，通过synchronized实现的。HashMap取值非常快”等等。这个时候说明他已经很熟练使用HashMap的工具了。
 * <p>
 * 简单的说HashMap的存储结构是由数组和链表共同完成的
 * HashMap是Y轴方向是数组，X轴方向就是链表的存储方式
 *
 * 几个关键的信息：基于Map接口实现、允许null键/值、非同步、不保证有序(比如插入的顺序)、也不保证序不随时间变化。
 */

public class HashmapAndHashSet {

   public static void main(String... args){

       HashMap<String, Integer> map = new HashMap<String, Integer>();
       map.put("语文", 1);
       map.put("数学", 2);
       map.put("英语", 3);
       map.put("历史", 4);
       map.put("政治", 5);
       map.put("地理", 6);
       map.put("生物", 7);
       map.put("化学", 8);
       map.put("语文", 1);
       map.put("语文", 1);
       for(Map.Entry<String, Integer> entry : map.entrySet()) {
           System.out.println(entry.getKey() + ": " + entry.getValue());
       }
   }
}
