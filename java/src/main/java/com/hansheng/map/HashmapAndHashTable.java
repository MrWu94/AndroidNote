package com.hansheng.map;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by hansheng on 16-10-17.
 * HashMap和Hashtable的区别
 * <p>
 * HashMap和Hashtable都实现了Map接口，但决定用哪一个之前先要弄清楚它们之间的分别。主要的区别有：线程安全性，同步(synchronization)，以及速度。
 * <p>
 * HashMap几乎可以等价于Hashtable，除了HashMap是非synchronized的，并可以接受null(HashMap可以接受为null的键值(key)和值(value)，
 * 而Hashtable则不行)。
 * HashMap是非synchronized，而Hashtable是synchronized，这意味着Hashtable是线程安全的，多个线程可以共享一个Hashtable；
 * 而如果没有正确的同步的话，多个线程是不能共享HashMap的。Java 5提供了ConcurrentHashMap，它是HashTable的替代，比HashTable的扩展性更好。
 * 另一个区别是HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。
 * 所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException，但迭代器本身的remove()
 * 方法移除元素则不会抛出ConcurrentModificationException异常。但这并不是一个一定发生的行为，要看JVM。这条同样也是Enumeration
 * 和Iterator的区别。
 * 由于Hashtable是线程安全的也是synchronized，所以在单线程环境下它比HashMap要慢。如果你不需要同步，只需要单一线程，
 * 那么使用HashMap性能要好过Hashtable。
 * HashMap不能保证随着时间的推移Map中的元素次序是不变的。
 * <p>
 * hashtable
 * 对键的规定:
 * 1.不允许有null键(也不允许有null值)
 * 2.如果键重复,后面添加的键值将会复盖前面的键
 * 打印顺序:
 * 按先进后出的顺序
 * <p>
 * HashTable是线程安全的，而HashMap不是
 * <p>
 * HashMap中允许存在null键和null值，而HashTable中不允许
 */

public class HashmapAndHashTable {
    public static void main(String... args) {
        Map<String, String> map =
                new HashMap<String, String>();
        String key1 = "caterpillar";
        String key2 = "justin";
        map.put(key1, "caterpillar的讯息");
        map.put(key2, "justin的讯息");

        System.out.println(map.get(key1));
        System.out.println(map.get(key2));

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("cn", "中国");
        hashMap.put("us", "米国");
        hashMap.put("en", "英国");
        hashMap.put("","");


        System.out.println(hashMap);
        System.out.println("cn" + hashMap.get("cn"));
        System.out.println(hashMap.containsKey("cn"));
        System.out.println(hashMap.keySet());
        System.out.println(hashMap.isEmpty());

        //hashMap.remove("cn");
        //System.out.println(hashMap.containsKey("cn"));

        //使用Iterator遍历HashMap
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            System.out.println("key:" + key);
            System.out.println("value:" + hashMap.get(key));
        }

        //使用Entry遍历HashMap
        Set<Map.Entry<String, String>> sets = hashMap.entrySet();
        for (Map.Entry<String, String> entry : sets) {
            System.out.println(entry.getKey() + ", ");
            System.out.println(entry.getValue());
        }

        Hashtable table = new Hashtable();
        table.put("key1", "value1");//键 和 值,
        table.put("key2", "value2");
        table.put("key3", "value3");//相当于堆栈 后进先出

        Enumeration e = table.keys();//创建枚举
        while (e.hasMoreElements()) {//是否有元素
            String key = (String) e.nextElement();
            System.out.println(key + " : " + table.get(key));
        }
    }

}
