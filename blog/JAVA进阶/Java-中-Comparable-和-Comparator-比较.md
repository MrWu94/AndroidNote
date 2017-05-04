###Comparable简介
Comparable是排序接口。
若一个实现了Comparable接口，就意味着“该类支持排序”。即实现Comparable接口的类支持排序。
Comparable定义
```
public interface Comparable<T>{
      public int compateTo(T o);
}
```
说明：假设我们通过 x.compareTo(y) 来“比较x和y的大小”。若返回“负数”，意味着“x比y小”；返回“零”，意味着“x等于y”；返回“正数”，意味着“x大于y”。

>Comparable
一个实现了comparable接口的对象的实例可以被用于和**相同对象的不同实例做对比。**它本身必须实现java.lang.Comparable的接口，这样它就拥有了对比的能力。

>Comparator
一个实现了comparator接口的对象能够**对比不同的对象**。它不能用于同一个类的不同实例的对比，但是可以用于其他的类的实例做对比。它必须实现java.util.Comparator的接口。

如何使用
在Java中有两个接口来实现Comparable和Comparator，每一个对象都有一个必须实现的接口。分别是：
**java.lang.Comparable: int compareTo(Object o1)**
这个方法用于**当前对象与o1对象做对比**，返回int值，分别的意思是：

**java.util.Comparator: int compare(Object o1, Objecto2)**
这个方法用于**o1与o2对象做对比**，返回int值，分别的意思是：

###Comparator简介
Comparator是一个比较接口
我们若需要控制某个类的次序，而该类本身不支持此排序，那么，我们可以建立一个“该类的比较器”来进行排序。这个比较器只需要实现Comparator接口即可
也就是说，我们可以通过“实现Comparator“来新建一个比较器，然后通过该比较器对类进行排序

Comparator定义
```
public interface Comparator<T>{
        int compare(T o1,T o2);
        boolean equals(Object obj);
}
```
>说明：(01) 若一个类要实现Comparator接口：它一定要实现compareTo(T o1, T o2) 函数，但可以不实现 equals(Object obj) 函数。
        为什么可以不实现 equals(Object obj) 函数呢？ 因为任何类，默认都是已经实现了equals(Object obj)的。 Java中的一切类都是继承于java.lang.Object，在Object.java中实现了equals(Object obj)函数；所以，其它所有的类也相当于都实现了该函数。
(02) int compare(T o1, T o2) 是“比较o1和o2的大小”。返回“负数”，意味着“o1比o2小”；返回“零”，意味着“o1等于o2”；返回“正数”，意味着“o1大于o2”。


**Comparator 和 Comparable 比较**
Comparable是排序接口；若一个类实现了Comparable接口，就意味着“该类支持排序”。而Comparator是比较器；我们若需要控制某个类的次序，可以建立一个“该类的比较器”来进行排序。
我们不难发现：Comparable相当于“内部比较器”，而Comparator相当于“外部比较器”。
```
import java.util.*;
import java.lang.Comparable;

/**
 * @desc "Comparator"和“Comparable”的比较程序。
 *   (01) "Comparable"
 *   它是一个排序接口，只包含一个函数compareTo()。
 *   一个类实现了Comparable接口，就意味着“该类本身支持排序”，它可以直接通过Arrays.sort() 或 Collections.sort()进行排序。
 *   (02) "Comparator"
 *   它是一个比较器接口，包括两个函数：compare() 和 equals()。
 *   一个类实现了Comparator接口，那么它就是一个“比较器”。其它的类，可以根据该比较器去排序。
 *
 *   综上所述：Comparable是内部比较器，而Comparator是外部比较器。
 *   一个类本身实现了Comparable比较器，就意味着它本身支持排序；若它本身没实现Comparable，也可以通过外部比较器Comparator进行排序。
 */
public class Test4{

    public static void main(String[] args) {
        // 新建ArrayList(动态数组)
        ArrayList<Person> list = new ArrayList<Person>();
        // 添加对象到ArrayList中
        list.add(new Person("ccc", 20));
        list.add(new Person("AAA", 30));
        list.add(new Person("bbb", 10));
        list.add(new Person("ddd", 40));

        // 打印list的原始序列
        System.out.printf("Original  sort, list:%s\n", list);

        // 对list进行排序
        // 这里会根据“Person实现的Comparable<String>接口”进行排序，即会根据“name”进行排序
        Collections.sort(list);
        System.out.printf("Name      sort, list:%s\n", list);

        // 通过“比较器(AscAgeComparator)”，对list进行排序
        // AscAgeComparator的排序方式是：根据“age”的升序排序
        Collections.sort(list, new AscAgeComparator());
        System.out.printf("Asc(age)  sort, list:%s\n", list);

        // 通过“比较器(DescAgeComparator)”，对list进行排序
        // DescAgeComparator的排序方式是：根据“age”的降序排序
        Collections.sort(list, new DescAgeComparator());
        System.out.printf("Desc(age) sort, list:%s\n", list);

        // 判断两个person是否相等
        testEquals();
    }

    /**
     * @desc 测试两个Person比较是否相等。
     *   由于Person实现了equals()函数：若两person的age、name都相等，则认为这两个person相等。
     *   所以，这里的p1和p2相等。
     *
     *   TODO：若去掉Person中的equals()函数，则p1不等于p2
     */
    private static void testEquals() {
        Person p1 = new Person("eee", 100);
        Person p2 = new Person("eee", 100);
        if (p1.equals(p2)) {
            System.out.printf("%s EQUAL %s\n", p1, p2);
        } else {
            System.out.printf("%s NOT EQUAL %s\n", p1, p2);
        }
    }

    /**
     * @desc Person类。
     *       Person实现了Comparable接口，这意味着Person本身支持排序
     */
    private static class Person implements Comparable<Person>{
        int age;
        String name;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String toString() {
            return name + " - " +age;
        }

        /**
         * 比较两个Person是否相等：若它们的name和age都相等，则认为它们相等
         */
        boolean equals(Person person) {
            if (this.age == person.age && this.name == person.name)
                return true;
            return false;
        }

        /**
         * @desc 实现 “Comparable<String>” 的接口，即重写compareTo<T t>函数。
         *  这里是通过“person的名字”进行比较的
         */
        public int compareTo(Person person) {
            return this.name.compareTo(person.name);
            //return this.name - person.name;
        }
    }

    /**
     * @desc AscAgeComparator比较器
     *       它是“Person的age的升序比较器”
     */
    private static class AscAgeComparator implements Comparator<Person> {
        
        public int compare(Person p1, Person p2) {
            return p1.getAge() - p2.getAge();
        }
    }

    /**
     * @desc DescAgeComparator比较器
     *       它是“Person的age的升序比较器”
     */
    private static class DescAgeComparator implements Comparator<Person> {
        
        public int compare(Person p1, Person p2) {
            return p2.getAge() - p1.getAge();
        }
    }

}
```
