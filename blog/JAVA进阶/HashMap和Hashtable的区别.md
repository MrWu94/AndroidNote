HashMap和Hashtable都实现了Map接口，但决定用哪个之前先要弄清楚它们之间的区别。主要的区别是：线程安全性，同步（synchronization）,以及速度。

hashcode()方法决定了对新啊会放到那个bucket里，当多个对象的hashcode值冲突时，equals()方法决定了这些对象是否是“同一个”对象。所以，如果要将自定义的对象放入到hashmap中或hashset中，要重写@Override hashcode()和equals()方法。

# get()

get(Object key)方法根据指定的key值返回对应的value，该方法调用了getEntry(Object key)得到相应的entry，然后返回entry.getValue()。因此getEntry()是算法的核心。
算法思想是首先通过hash()函数得到对应bucket的下标，然后依次遍历冲突链表，**通过key.equals(k)方法来判断是否是要找的那个entry。**
