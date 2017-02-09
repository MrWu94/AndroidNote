package com.hansheng.Basic;

/**
 * Created by mrwu on 2017/2/8.
 * <p>
 * JVM中一个字节以下的整型数据会在JVM启动的时候加载进内存，除非用new Integer()显式的创建对象，否则都是同一个对象
 * 所有只有i04是一个新对象，其他都是同一个对象。所有A，B选项为true
 * C选项i03和i04是两个不同的对象，返回false
 * D选项i02是基本数据类型，比较的时候比较的是数值，返回true
 */

public class IntegerText {
    public static void main(String... args) {
        Integer i01 = 59;
        int i02 = 59;
        Integer i03 =Integer.valueOf(59);
        Integer i04 = new Integer(59);
        Integer n1 = new Integer(47);
        Integer n2 = new Integer(47);
        System.out.print(n1 == n2);
        System.out.print(",");
        System.out.println(n1 != n2);

        System.out.println(i01== i02);
        System.out.println(i01== i03);
        System.out.println(i03== i04);
        System.out.println(i02== i04);
    }
}
