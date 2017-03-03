package com.hansheng.OutterandInnerClass.InnerandOutter;

/**
 * Created by hansheng on 17-3-3.
 */

class OutterClass {
    private int in = 0;
    static int inn=4;
    public OutterClass(int in) {
        InnerClass inner=new InnerClass();
        this.in=inner.innerNum;
    }
    class InnerClass {    //内部类
        public int innerNum=1;
        public void output() {
            System.out.println(in);
            System.out.println(inn);
            int a=OutterClass.this.inn;
        }
    }
}