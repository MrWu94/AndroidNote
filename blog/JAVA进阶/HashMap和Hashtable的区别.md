## HashMap的底层主要是基于数组和链表来实现的，它之所以有相当快的查询速度主要是因为它是通过计算散列码来决定存储的位置。

## HashMap中主要是通过key的hashCode来计算hash值的，只要hashCode相同，计算出来的hash值就一样。


HashMap和Hashtable都实现了Map接口，但决定用哪个之前先要弄清楚它们之间的区别。主要的区别是：线程安全性，同步（synchronization）,以及速度。

hashcode()方法决定了对新加的值会放到那个bucket里，当多个对象的hashcode值冲突时，equals()方法决定了这些对象是否是“同一个”对象。所以，如果要将自定义的对象放入到hashmap中或hashset中，要重写@Override hashcode()和equals()方法。

# get()

get(Object key)方法根据指定的key值返回对应的value，该方法调用了getEntry(Object key)得到相应的entry，然后返回entry.getValue()。因此getEntry()是算法的核心。
算法思想是首先通过hash()函数得到对应bucket的下标，然后依次遍历冲突链表，**通过key.equals(k)方法来判断是否是要找的那个entry。**

1、HashCode的特性

（1）HashCode的存在主要是用于查找的快捷性，如Hashtable，HashMap等，HashCode经常用于确定对象的存储地址。

（2）如果两个对象相同，?equals方法一定返回true，并且这两个对象的HashCode一定相同。

（3）两个对象的HashCode相同，并不一定表示两个对象就相同，即equals()不一定为true，只能够说明这两个对象在一个散列存储结构中。

（4）如果对象的equals方法被重写，那么对象的HashCode也尽量重写。


## ArrayList、LinkedList、Vector的区别

List的三个子类的特点

# ArrayList:

底层数据结构是数组，查询快，增删慢。

线程不安全，效率高。

## Vector:

底层数据结构是数组，查询快，增删慢。

线程安全，效率低。

Vector相对ArrayList查询慢(线程安全的)。

Vector相对LinkedList增删慢(数组结构)。

## LinkedList

底层数据结构是链表，查询慢，增删快。

线程不安全，效率高。

## Vector和ArrayList的区别

Vector是线程安全的,效率低。

ArrayList是线程不安全的,效率高。

共同点:底层数据结构都是数组实现的,查询快,增删慢。

## ArrayList和LinkedList的区别

ArrayList底层是数组结果,查询和修改快。

LinkedList底层是链表结构的,增和删比较快,查询和修改比较慢。

共同点:都是线程不安全的

List有三个子类使用

查询多用ArrayList。

增删多用LinkedList。

如果都多ArrayList。





参考：

http://blog.csdn.net/fighterandknight/article/details/61624150

http://blog.csdn.net/seu_calvin/article/details/52094115
