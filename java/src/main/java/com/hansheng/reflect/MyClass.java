package com.hansheng.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hansheng on 2016/7/17.
 */
public class MyClass {
    public int count;
    public MyClass(int start) {
        count = start;
    }
    public void increase(int step) {
        count = count + step;
    }

    public static void main(String... args){
        MyClass myClass=new MyClass(0);
        myClass.increase(2);
        System.out.println("Normal -> " + myClass.count);
        try {
            Constructor constructor=MyClass.class.getConstructor(int.class); //获取构造方法
            MyClass myClassReflect= (MyClass) constructor.newInstance(10);//创建对象
            Method method=MyClass.class.getMethod("increase",int.class);//获取方法
            method.invoke(myClassReflect,5);//调用方法
            Field field= MyClass.class.getField("count");//获取域
            System.out.println("Reflect -> " + field.getInt(myClassReflect)); //获取域的值
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}
