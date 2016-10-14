package com.hansheng.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by hansheng on 16-10-14.
 * 反射的概念
 * 主要是指程序可以访问，检测和修改它本身状态或行为的一种能力，并能根据自身行为的状态和结果，调整或修改应用所描述行为的状态和相关的语义。
 * <p>
 * <p>
 * 反射的作用
 * 反射可以让我们在运行时获取类的属性，方法，构造方法、父类、接口等信息，
 * 通过反射还可以让我们在运行期实例化对象、调用方法、即使方法或属性是私有的的也可以通过反射的形式调用。
 */

public class reflect {

    private static int i;

    public static void main(String... args) {

        getClassObj();
        getFieldsInfo();
        getMethodsInfo();
        modifyFieldValue();
    }

    private static void getClassObj() {
        Class<?> cls1 = reflect.class;

        reflect activity = new reflect();
        Class<?> cls2 = activity.getClass();

        Class<?> cls3 = null;
        try {
            cls3 = Class.forName("com.hansheng.reflect.reflect");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("cls1: ").append(cls1).append("\n\n");
        sb.append("cls2: ").append(cls2).append("\n\n");
        sb.append("cls3: ").append(cls3);
        System.out.println("getClass=" + sb);
    }

    private static void getFieldsInfo() {
        Class<reflect> cls = reflect.class;
        Field[] fields = cls.getDeclaredFields();
        if (fields == null) return;

        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            sb.append(Modifier.toString(field.getModifiers())).append(" ");
            sb.append(field.getType().getSimpleName()).append(" ");
            sb.append(field.getName()).append(";");
            sb.append("\n\n");
        }
        System.out.println("getField=" + sb);

    }

    private static void getMethodsInfo() {
        Class<reflect> cls = reflect.class;
        Method[] methods = cls.getDeclaredMethods();
        if (methods == null) return;

        StringBuilder sb = new StringBuilder();
        for (Method method : methods) {
            sb.append(Modifier.toString(method.getModifiers())).append(" ");
            sb.append(method.getReturnType()).append(" ");
            sb.append(method.getName()).append("(");
            Class[] parameters = method.getParameterTypes();
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    Class paramCls = parameters[i];
                    sb.append(paramCls.getSimpleName());
                    if (i < parameters.length - 1) sb.append(", ");
                }
            }
            sb.append(")\n\n");
        }
        System.out.println("getMethod=" + sb);


    }

    private static void modifyFieldValue() {
        Class<reflect> cls = reflect.class;
        Field[] fields = cls.getDeclaredFields();
        if (fields == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("获得类的所有属性信息：\n\n");
        for (Field field : fields) {
            sb.append(Modifier.toString(field.getModifiers())).append(" ");
            sb.append(field.getType().getSimpleName()).append(" ");
            sb.append(field.getName()).append(";");
            sb.append("\n\n");
        }

        try {
            sb.append("属性i的默认值：i = ");
            Field f = cls.getDeclaredField("i");
            sb.append(f.getInt("i")).append("\n\n");
            f.set("i", 100);
            sb.append("属性i修改后的值：i = ");
            sb.append(f.getInt("i")).append("\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("modifyfield=" + sb);


    }
}
