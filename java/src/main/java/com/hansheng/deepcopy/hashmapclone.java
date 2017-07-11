package com.hansheng.deepcopy;

import java.util.HashMap;

/**
 * Created by hansheng on 17-3-21.
 *
 * http://www.importnew.com/19847.html
 * //浅拷贝与深拷贝
 * //浅拷贝:被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象。
 * //换言之，浅复制仅仅复制所考虑的对象，而不复制它所引用的对象。
 * //深拷贝：被复制对象的所有变量都含有与原来的对象相同的值，除去那些引用其他对象的变量。
 * //那些引用其他对象的变量将指向被复制过的新对象，而不再是原有的那些被引用的对象。
 * //换言之，深复制把要复制的对象所引用的对象都复制了一遍。
 * //1、直接赋值（字符串外都属于浅拷贝）
 * //2、使用构造函数（深拷贝）
 * //3、使用clone()方法（深拷贝）
 * <p>
 * //字符串(不理解无colne()方法)
 *
 *
 *  也就是说浅拷贝只复制一个对象，传递引用，不能复制实例。而深拷贝对对象内部的引用均复制，它是创建一个新的实例，并且复制实例。
 *
 *  对于浅拷贝当对象的成员变量是基本数据类型时，两个对象的成员变量已有存储空间，赋值运算传递值，所以浅拷贝能够复制实例。
 *  但是当对象的成员变量是引用数据类型时，就不能实现对象的复制了。
 *
 *  为什么使用克隆

 很重要并且常见的常见就是：某个API需要提供一个List集合，但是又不希望调用者的修改影响到自身的变化，
 因此需要克隆一份对象，以此达到数据隔离的目的。
 */

public class hashmapclone {
    public static void main(String[] args) {
        //HashMap
        HashMap h = new HashMap();
        h.put("1", "hhh");
        HashMap m = h;//浅拷贝，会改变原数据的值
        HashMap p = new HashMap(h);//深拷贝不会改变原数据的值
        HashMap n = (HashMap) h.clone();//深拷贝
        System.out.println("h:" + h.get("1") + " m:" + m.get("1") + " p:" + p.get("1") + " n:" + n.get("1"));
        m.put("1", "mmm");
        p.put("1", "ppp");
        n.put("1", "nnn");
        System.out.println("h:" + h.get("1") + " m:" + m.get("1") + " p:" + p.get("1") + " n:" + n.get("1"));
    }
}
