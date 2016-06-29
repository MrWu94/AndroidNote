package com.hansheng.FinalText;

import java.util.Random;

/**
 * Created by hansheng on 2016/6/27.
 */
public class FinalText {

    private final String final01="hansheng"; //编译期常量，必须要进行初始化，且不可更改
    private final String final02;//构造器常量，在实例化一个对象时被初始化

    private static Random random=new Random();
    private final int final03=random.nextInt(50);//使用随机数来进行初始化
//基本数据类型不可变的是其内容，而引用数据类型不可变的是其引用，引用所指定的对象内容是可变的。
    public final Person final04=new Person("hansheng");//final指向引用数据类型


    public FinalText(String final02) {
        this.final02 = final02;
    }

    @Override
    public String toString() {
        return "final01="+final01+"   final02="+final02+"   final03="+final03+"    final04="+final04.getName();
    }

    public static void main(String[] args){

        System.out.println("---------第一次创建对象------");
        FinalText final1=new FinalText("cm");
        System.out.println(final1);
        System.out.println("---------第二次创建对象------");
        FinalText final2=new FinalText("zj");
        System.out.println(final1);
        System.out.println("---------修改引用对象------");
        final2.final04.setName("hanming");
        System.out.println(final2);
    }
}
