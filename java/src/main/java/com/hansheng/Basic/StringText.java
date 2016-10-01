package com.hansheng.Basic;

import java.util.Calendar;

/**
 * Created by hansheng on 2016/9/30.
 */

public class StringText {
    static int x = 10;

    static {
        x += 5;
    }

    static {
        x /= 3;
    }

    public static void main(String... args) {
        stringText();
        int i = 1;
        System.out.println(fina(i));
        A classa = new A("he");
        A classb = new A("he");
        System.out.println(new A("he") == new A("he"));
        System.out.println(classa == classb);
        System.out.println("x=" + x);

        String s1 = "abc" + "def";//1		³Ø
        String s2 = new String(s1);//2	¶Ñ
        if (s1.equals(s2))//3
            System.out.println(".equals succeeded");//4
        if (s1 == s2)//5					X
            System.out.println("==succeeded");//6
        int y, m, d, h, mi, s, nowsj;
        Calendar calendar = Calendar.getInstance();
        y = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d = calendar.get(Calendar.DATE);
        h = calendar.get(Calendar.HOUR_OF_DAY);
        mi = calendar.get(Calendar.MINUTE);
        s = calendar.get(Calendar.MINUTE);
        nowsj = y * 100 + m;
        System.out.println(y + "-" + m + "-" + d + "-" + h + "-" + nowsj);

        String s3 = " Hello ";
        String s4;
        s3 += " World ";
        s3.trim();
        s4 = s3.trim();
        System.out.println(s3);
        System.out.println(s4);

        int i1 = 3000;
        Integer j = new Integer(3000);

        System.out.println(i1 == j);
        //自动拆箱

        System.out.println(j.equals(i1));
        //自动装箱
        //Integer的equals方法比较的是值不是地址
        //String,Integer,Date在这些类当中equals有其自身的实现，而不再是比较类在堆内存中的存放地址了。


    }

    public static void stringText() {
        String x = "fmn";
        x.toUpperCase();
        System.out.println(x.toUpperCase());
        System.out.println(x);
        String y = x.replace('f', 'F');
        System.out.println(y);
        y = y + "wxy";
        System.out.println(y);
    }

    public static int fina(int i) {
        try {
            i += 10;
            return i;
        } catch (Exception e) {

        } finally {
            i += 10;
            return i;
        }
    }


}

class A {
    public A(String str) {
    }
}
