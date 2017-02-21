package com.hansheng.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hansheng on 17-2-20.
 * http://www.cnblogs.com/chenssy/p/3308489.html
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
 * <p>
 * 浅拷贝：使用一个已知实例对新创建实例的成员变量逐个赋值，这个方式被称为浅拷贝。
 * <p>
 * 深拷贝：当一个类的拷贝构造方法，不仅要复制对象的所有非引用成员变量值，还要为引用类型的成员变量创建新的实例，
 * 并且初始化为形式参数实例值。这个方式称为深拷贝
 */

public class CloneDemo {

    public static void main(String... args) {
        String s = "sss";
        String t = s;   //深拷贝
        String y = new String(s); //深拷贝
        System.out.println("s:" + s + " t:" + t + " y:" + y);
        t = "ttt";
        y = "yyy";
        System.out.println("s:" + s + " t:" + t + " y:" + y);

        //数组
        String[] ss = {"111", "222", "333"};
        String[] tt = ss; //浅拷贝
        String[] ww = (String[]) ss.clone();//深拷贝
        System.out.println("ss:" + ss[1] + " tt:" + tt[1] + " ww:" + ww[1]);
        tt[1] = "2t2";
        ww[1] = "2w2";
        System.out.println("ss:" + ss[1] + " tt:" + tt[1] + " ww:" + ww[1]);


        //list列表
        ArrayList a = new ArrayList();
        for (int i = 0; i < 10; i++) {
            a.add(String.valueOf(i + 1));
        }
        ArrayList b = a;//浅拷贝
        ArrayList c = new ArrayList(a);//深拷贝
        ArrayList d = (ArrayList) a.clone();//深拷贝
        System.out.println("a:" + a.get(1) + " b:" + b.get(1) + " c:" + c.get(1) + " d:" + d.get(1));
        b.set(1, "bbb");
        c.set(1, "ccc");
        System.out.println("a:" + a.get(1) + " b:" + b.get(1) + " c:" + c.get(1) + " d:" + d.get(1));

        //HashMap
        HashMap h = new HashMap();
        h.put("1", "hhh");
        HashMap m = h;//浅拷贝
        HashMap p = new HashMap(h);//深拷贝
        HashMap n = (HashMap) h.clone();//深拷贝
        System.out.println("h:" + h.get("1") + " m:" + m.get("1") + " p:" + p.get("1") + " n:" + n.get("1"));
        m.put("1", "mmm");
        p.put("1", "ppp");
        n.put("1", "nnn");
        System.out.println("h:" + h.get("1") + " m:" + m.get("1") + " p:" + p.get("1") + " n:" + n.get("1"));
    }
}
