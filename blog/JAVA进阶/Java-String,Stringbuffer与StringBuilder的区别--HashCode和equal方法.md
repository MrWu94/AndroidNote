+ 都是final类，都不允许继承
+ String长度是不可变得，StringBuffer、StringBuilder长度都可变的
+ StringBuffer是线程安全的，StringBuilder不是线程安全的

StringBuilder>StringBuffer>String

StringBuffer类每次结果都会对StringBuffer对象本身进行操作，而不是生成新的对象。


> equals()反映的是对象或变量具体的值，即两个对象里面包含的值--可能是对象的引用，也可能是值类型的值。而hashCode()是对象或变量通过哈希算法计算出的哈希值。

>之所以有hashCode方法，是因为在批量的对象比较中，hashCode要比equals来得快，很多集合都用到了hashCode，比如HashTable。

>两个obj，如果equals()相等，hashCode()一定相等。

>两个obj，如果hashCode()相等，equals()不一定相等（Hash散列值有冲突的情况，虽然概率很低）。

1、首先equals()和hashcode()这两个方法都是从object类中继承过来的。
equals()是对两个对象的地址值进行的比较（即比较引用是否相同）。
hashCode()是一个本地方法，它的实现是根据本地机器相关的。


**equals()相等的两个对象，hashcode()一定相等；反过来：hashcode()不等，一定能推出equals()也不等；hashcode()相等，equals()可能相等，也可能不等。 **

>1、为什么要重载equal方法？
>答案：因为Object的equal方法默认是两个对象的引用的比较，意思就是指向同一内存,地址则相等，否则不相等；如果你现在需要利用对象里面的值来判断是否相等，则重载equal方法。

```
public class Test {
    public static void main(String[] args) {
        HashSet<Demo1> set = new HashSet<Demo1>();
        HashSet<String> set1=new HashSet<String>();
        for (int i = 0; i <= 3; i++){
            set.add(new Demo1(i+5,i+2));           
        }
        set1.add("hansheng");
        set1.add("hansheng");
        //重复的元素hashset自动判断
         System.out.println(set1);
        System.out.println(set);
        //传入的是对象
        set.add(new Demo1(1+4,2));
        set.add(new Demo1(1,3));
        System.out.println("add="+set);
        System.out.println("contain="+set.contains(new Demo1(0,0)));
        System.out.println(set.add(new Demo1(1,1)));
        System.out.println(set.add(new Demo1(4,4)));
        System.out.println(set);   
}
    private static class Demo1 {
        private int value;
       
        private int id;
  
        public Demo1(int value,int id) {
            this.value = value;
            this.id=id;
        }
        @Override
        public String toString() {
            return " value = " + value;
        }
        @Override
        public boolean equals(Object o) {
            Demo1 a = (Demo1) o;
            System.out.println((a.value == value)+"   a.value="+a.value+"   value="+value);
            //hashcode值相等时，判断a.value传入的对象值得内容content是否与this.valuehashcode值中的content是否相等，相等则hashset不添加。
            return (a.value == this.value) ? true : false;
        }
      //hashcode值相等时，会调用equal()；此时传入的对象就是值相等的
       @Override
        public int hashCode() {
        	System.out.println("id="+id);
            return id;
        }
    }
}
```
1、hashCode的存在主要是用于查找的快捷性，如Hashtable，HashMap等，hashCode是用来在散列存储结构中确定对象的存储地址的；
2、Map（set）的结构能够快速找到一个对象，而不是进行较慢的线性查找。使用hash过的键来定位对象分两步。Map(set)可以看作是数组的数组。第一个数组的索引就是对键采用hashCode()计算出来的值，再在这个位置上查找第二个数组，使用键的equals()方法来进行线性查找，直到找到要找的对象。

3、Java中equals()和hashCode()有一个契约：
1. 如果两个对象相等的话，它们的hash code必须相等；
2. 但如果两个对象的hash code相等的话，这两个对象不一定相等。
