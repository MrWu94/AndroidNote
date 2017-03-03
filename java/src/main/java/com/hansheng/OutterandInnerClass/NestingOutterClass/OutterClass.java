package com.hansheng.OutterandInnerClass.NestingOutterClass;

/**
 * Created by hansheng on 17-3-3.
 * 嵌套内部类，就是修饰为static的内部类。声明为static的内部类，不需要内部类对象和外部类对象之间的联系，
 * 就是说我们可以直接引用outer.inner，即不需要创建外部类，也不需要创建内部类。
 */
class OutterClass {
    public OutterClass(int in) {
    }

    static class InnerClass {    //局部内部类
        int innerNum = 1;
    }
}

class classa {
    public static void main() {
        OutterClass.InnerClass in = new OutterClass.InnerClass();
    }
}