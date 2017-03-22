package com.hansheng.string;

/**
 * Created by hansheng on 17-3-21.
 * 产生了三个对象，，其中两个对象不可达对象从而被垃圾回收器回收
 *  字符串对象（String Object）是非可变的（immutable）
 */

public class StringImmutable {
    public static void main(String[] args){
        String s = " Hello ";
        s += " World ";
        s.trim( );
        System.out.println(s);
    }
}
