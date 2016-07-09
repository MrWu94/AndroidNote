package com.hansheng.deepcopy;

/**
 * Created by hansheng on 2016/7/9.
 * 浅拷贝：使用一个已知实例对新创建实例的成员变量逐个赋值，这个方式被称为浅拷贝。
 * 浅拷贝只复制一个对象，传递引用，不能复制实例
 * <p/>
 * 深拷贝：当一个类的拷贝构造方法，不仅要复制对象的所有非引用成员变量值，还要为引用类型的成员变量创建新的实例，并且初始化为形式参数实例值。这个方式称为深拷贝
 * 深拷贝对对象内部的引用均复制，它是创建一个新的实例，并且复制实例。
 * 1、基本类型
 * <p/>
 * 如果变量是基本很类型，则拷贝其值，比如 int、float 等。
 * <p/>
 * 2、对象
 * <p/>
 * 如果变量是一个实例对象，则拷贝其地址引用，也就是说此时新对象与原来对象是公用该实例变量。
 * <p/>
 * 3、String 字符串
 * <p/>
 * 若变量为 String 字符串，则拷贝其地址引用。但是在修改时，它会从字符串池中重新生成一个新的字符串，原有字符串对象保持不变。
 */
public class Client1 {
    public static void main(String[] args) {
        //写封邮件
        Email email = new Email("请参加会议", "请与今天12:30到二会议室参加会议...");

        Person person1 = new Person("张三", email);

        Person person2 = person1.clone();
        person2.setName("李四");
        Person person3 = person1.clone();
        person3.setName("王五");

        person1.getEmail().setContent("请与今天12:00到二会议室参加会议...");

        System.out.println(person1.getName() + "的邮件内容是：" + person1.getEmail().getContent());
        System.out.println(person2.getName() + "的邮件内容是：" + person2.getEmail().getContent());
        System.out.println(person3.getName() + "的邮件内容是：" + person3.getEmail().getContent());
    }
}