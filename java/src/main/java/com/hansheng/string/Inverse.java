package com.hansheng.string;

/**
 * Created by hansheng on 2016/7/19.
 * 实现字符串的反转
 *
 * 什么是字符串常量池
 JVM为了减少字符串对象的重复创建，其维护了一个特殊的内存，这段内存被成为字符串常量池或者字符串字面量池

 工作原理
 当代码中出现字面量形式创建字符串对象时，JVM首先会对这个字面量进行检查，如果字符串常量池中存在相同内容的字符串对象的引用，则将这个引用返回，否则新的字符串对象被创建，然后将这个引用放入字符串常量池，并返回该引用。

 实现前提
 字符串常量池实现的前提条件就是Java中String对象是不可变的，这样可以安全保证多个变量共享同一个对象。如果Java中的String对象可变的话，一个引用操作改变了对象的值，那么其他的变量也会受到影响，显然这样是不合理的。

 更详细的关于字符串常量池  http://droidyue.com/blog/2014/12/21/string-literal-pool-in-java/

 关于堆和栈
 Java中所有由类实例化的对象和数组都存放在堆内存中，无论是成员变量，局部变量，还是类变量，它们指向的对象都存储在堆内存中。而栈内存用来存储局部变量和方法调用。
 更详细的关于堆和栈的区别 http://droidyue.com/blog/2014/12/07/differences-between-stack-and-heap-in-java/

 关于寄存器
 Java中运行时数据区有一个程序寄存器（又称程序计数器），该寄存器为线程私有。Java中的程序计数器用来记录当前线程中正在执行的指令。如果当前正在执行的方法是本地方法，那么此刻程序计数器的值为undefined
 关于JVM运行时数据区的 http://droidyue.com/blog/2014/12/21/java-runtime-data-areas/
 */
public class Inverse {
    public static void main(String... args) {
        System.out.println(inverse("hansheng"));
    }

    private static String inverse(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length/2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = temp;
        }
        return String.copyValueOf(chars);
    }
}
