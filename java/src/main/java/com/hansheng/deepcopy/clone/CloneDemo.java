package com.hansheng.deepcopy.clone;

/**
 * Created by mrwu on 2017/7/11.
 * <p>
 * http://droidyue.com/blog/2016/05/15/dive-into-java-clone/
 * <p>
 * Java中的赋值
 * <p>
 * 在Java中，赋值是很常用的，一个简单的赋值如下
 * <p>
 * //原始类型
 * int a = 1;
 * int b = a;
 * <p>
 * //引用类型
 * String[] weekdays = new String[5];
 * String[] gongzuori = weekdays;//仅拷贝引用
 * 在上述代码中。
 * <p>
 * 如果是原始数据类型，赋值传递的为真实的值
 * 如果是引用数据类型，赋值传递的为对象的引用，而不是对象。
 * 了解了数据类型和引用类型的这个区别，便于我们了解clone。
 * <p>
 * 关于深拷贝的一些特点
 * <p>
 * 需要重写clone方法，不仅仅只调用父类的方法，还需调用属性的clone方法
 * 做到了原对象与克隆对象之间100%数据分离
 * 如果是对象存在引用类型的属性，建议使用深拷贝
 * 深拷贝比浅拷贝要更加耗时，效率更低
 * <p>
 * 浅拷贝
 * 上面的代码实现的clone实际上是属于浅拷贝（Shallow Copy）。
 * <p>
 * 关于浅拷贝，你该了解的
 * <p>
 * 使用默认的clone方法
 * 对于原始数据域进行值拷贝
 * 对于引用类型仅拷贝引用
 * 执行快，效率高
 * 不能做到数据的100%分离。
 * 如果一个对象只包含原始数据域或者不可变对象域，推荐使用浅拷贝。
 * <p>
 * <p>
 * 为什么使用克隆
 * <p>
 * 很重要并且常见的常见就是：某个API需要提供一个List集合，
 * 但是又不希望调用者的修改影响到自身的变化，因此需要克隆一份对象，以此达到数据隔离的目的。
 */

public class CloneDemo {
    public static void main(String... args) {

        CloneableImp imp1 = new CloneableImp();
        imp1.child = new Child("Andy");
        try {
            Object obj = imp1.clone();
            CloneableImp imp2 = (CloneableImp) obj;
            imp2.child.name = "Bob";

            System.out.println("main imp1.child.name=" + imp1.child.name);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
