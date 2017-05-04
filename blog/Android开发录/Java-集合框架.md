Java集合框架大致可以分为五个部分：List列表，Set集合、Map映射、迭代器、工具类

**List 接口**通常表示一个列表（数组、队列、链表
栈），其中的元素**可以重复**的是：ArrayList 和LinkedList,另外还有不常用的Vector。LinkedList实现来Queue接口，因此也可以作为队列使用。

Set接口通常表示一个集合，其中的元素**不可以重复**（通过hashcode和equals函数保证），常用的实现类有HashSet和TreeSet

**Map**是一个**映射接口**，其中的每个元素都是一个**key-value键值对**，同样抽象类AbstractMap通过适配器模式实现了Map接口中的大部分函数，TreeMap
HashMap、WeakHashMap等实现类都通过继承AbstractMap实现的，另外，不常用的HashTable直接实现了Map接口。

Iterator是遍历集合的迭代器（不能遍历Map，只用来遍历Collection），Collection的实现类都实现了iterator()函数，它返回一个Iterator对象，用来遍历集合，ListIterator则专门用来遍历List。

## ArrayList
 ArrayList的内部实现是基于基础的对象数组的
特点：
- 顺序表
- 大小可变
- 允许null元素
- 线程不同步、查询速度快、增删满

## LinkedList
LinkedList: 内部采用链表来存储元素，支持快速插入/删除元素，但不支持高效地随机访问

 一般大家都知道ArrayList和LinkedList的大致区别：
 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
 2.对于随机访问get和set，ArrayList优于LinkedList，因为LinkedList要移动指针。
3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。**ArrayList的内部实现是基于基础的对象数组的**, LinkedList中的get方法是按照顺序从列表的一端开始检查，直到另外一端。对LinkedList而言，访问列表中的某个指定元素没有更快的方法了。**它们之间最主要的区别在于ArrayList是可改变大小的数组，而LinkedList是双向链接串列(doubly LinkedList)** ,因为Array是基于索引(index)的数据结构，它使用索引在数组中搜索和读取数据是很快的。Array获取数据的时间复杂度是O(1),但是要删除数据却是开销很大的，因为这需要重排数组中的所有数据。


```java
public class ArraylistandLinkedlist {
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

```

## HashSet
HashSet实现了Set接口，它**不允许**集合中有**重复的值**，当我们提到HashSet时，第一件事情就是在将对象存储在HashSet之前， 要先确保对象**重写equals()和hashCode()方法**，这样才能比较对象的值是否相等，以确保set中没有储存相等的对象。如果我们没有重写这两个方法，将会使用这个方法的默认实现。
特点
- 线程同步
- 不允许重复的值

## HashMap
HashMap实现了Map接口，Map接口对键值对进行映射。**Map中不允许重复的键**。Map接口有两个基本的实现，HashMap和TreeMap。TreeMap保存了对象的排列次序，而HashMap则不能。**HashMap允许键和值为null。**HashMap是非synchronized的，但collection框架提供方法能保证HashMap synchronized，这样多个线程同时访问HashMap时，能保证只有一个线程更改Map。

 HashMap是一种<key,value>的存储结构，能够快速将key的数据put方式存储起来，然后很快的通过get取出来”，“HashMap不是线程安全的， HashTable是线程安全的，通过synchronized实现的
简单的说HashMap的存储结构是由**数组和链表**共同完成的
 **HashMap是Y轴方向是数组，X轴方向就是链表的存储方式**

## HashTable
 HashMap和Hashtable都实现了Map接口，但决定用哪一个之前先要弄清楚它们之间的分别。主要的区别有：线程安全性，同步(synchronization)，以及速度。

 HashMap几乎可以等价于Hashtable，除了HashMap是非synchronized的，并可以接受null(HashMap可以接受为null的键值(key)和值(value)， 而Hashtable则不行)。**HashMap是非synchronized，而Hashtable是synchronized**，这意味着Hashtable是线程安全的，多个线程可以共享一个Hashtable；而如果没有正确的同步的话，多个线程是不能共享HashMap的。Java 5提供了ConcurrentHashMap，它是HashTable的替代，比HashTable的扩展性更好。另一个区别是HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException，但迭代器本身的remove()方法移除元素则不会抛出ConcurrentModificationException异常。但这并不是一个一定发生的行为，要看JVM。
** 由于Hashtable是线程安全的也是synchronized，所以在单线程环境下它比HashMap要慢。如果你不需要同步，只需要单一线程，那么使用HashMap性能要好过Hashtable。HashMap不能保证随着时间的推移Map中的元素次序是不变的。
```java
public class HashmapAndHashTable {
    public static void main(String... args) {

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

```










