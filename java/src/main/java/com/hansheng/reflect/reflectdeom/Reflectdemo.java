package com.hansheng.reflect.reflectdeom;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by hansheng on 16-10-17.
 */

public class Reflectdemo {
    public static void main(String ...args){
        Reflectdemo reflectdemo=new Reflectdemo();
        reflectdemo.getAllFields();
        reflectdemo.getClassObject();
        reflectdemo.getMethodInfo();
        reflectdemo.getFieldInfo();
        reflectdemo.getAllMethods();
        reflectdemo.getSuperClass();
        reflectdemo.getInterfaces();


    }

    private static void LogE(String str){
        System.out.println(str);
    }

    private void getClassObject(){
        Class<?> classObject = null;

        classObject = getClassObject_1();
        LogE("classObject_1 name : " + classObject.getName());
        classObject = getClassObject_2();
        LogE("classObject_2 name : " + classObject.getName());
        classObject = getClassObject_3();
        LogE("classObject_3 name : " + classObject.getName());
    }

    private void getMethodInfo(){
        getAllMethods();
        getCurrentClassMethods();
    }

    private void getFieldInfo(){
        getAllFields();
        getCurrentClassFields();
    }

    private void getSuperClass(){
        ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
        Class<?> superClass = programMonkey.getClass().getSuperclass();
        while (superClass != null) {
            LogE("programMonkey's super class is : " + superClass.getName());
            // 再获取父类的上一层父类，直到最后的 Object 类，Object 的父类为 null
            superClass = superClass.getSuperclass();
        }
    }

    private void getInterfaces() {
        ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
        Class<?>[] interfaceses = programMonkey.getClass().getInterfaces();
        for (Class<?> class1 : interfaceses) {
            LogE("programMonkey's interface is : " + class1.getName());
        }
    }

    private void compareCallMethodAndField(){
        long callMethodCostTime = getCallMethodCostTime(10000);
        LogE("callMethodCostTime == " + callMethodCostTime);
        long callFieldCostTime = getCallFieldCostTime(10000);
        LogE("callFieldCostTime == " + callFieldCostTime);
    }

    private long getCallMethodCostTime(int count){
        long startTime = System.currentTimeMillis();
        for(int index = 0 ; index < count; index++){
            ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
            try{
                Method setmLanguageMethod = programMonkey.getClass().getMethod("setmLanguage", String.class);
                setmLanguageMethod.setAccessible(true);
                setmLanguageMethod.invoke(programMonkey, "Java");
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }catch(InvocationTargetException e){
                e.printStackTrace();
            }catch(NoSuchMethodException e){
                e.printStackTrace();
            }
        }

        return System.currentTimeMillis()-startTime;
    }

    private long getCallFieldCostTime(int count){
        long startTime = System.currentTimeMillis();
        for(int index = 0 ; index < count; index++){
            ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
            try{
                Field ageField = programMonkey.getClass().getDeclaredField("mLanguage");
                ageField.set(programMonkey, "Java");
            }catch(NoSuchFieldException e){
                e.printStackTrace();
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }
        }

        return System.currentTimeMillis()-startTime;
    }

    /**
     * 获取当前类中的所有方法
     *
     * */
    private void getCurrentClassMethods() {
        ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
        Method[] methods = programMonkey.getClass().getDeclaredMethods();
        for (Method method : methods) {
            LogE("declared method name : " + method.getName());
        }

        try {
            Method getSalaryPerMonthMethod = programMonkey.getClass().getDeclaredMethod("getSalaryPerMonth");
            getSalaryPerMonthMethod.setAccessible(true);
            // 获取返回类型
            Class<?> returnType = getSalaryPerMonthMethod.getReturnType();
            LogE("getSalaryPerMonth 方法的返回类型 : " + returnType.getName());

            // 获取方法的参数类型列表
            Class<?>[] paramClasses = getSalaryPerMonthMethod.getParameterTypes() ;
            for (Class<?> class1 : paramClasses) {
                LogE("getSalaryPerMonth 方法的参数类型 : " + class1.getName());
            }

            // 是否是 private 函数，属性是否是 private 也可以使用这种方式判断
            LogE(getSalaryPerMonthMethod.getName() + " is private " + Modifier.isPrivate(getSalaryPerMonthMethod.getModifiers()));

            // 执行方法
            Object result = getSalaryPerMonthMethod.invoke(programMonkey);
            LogE("getSalaryPerMonth 方法的返回结果: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前类和父类的所有公有方法
     *
     * */
    private void getAllMethods() {
        ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
        // 获取当前类和父类的所有公有方法
        Method[] methods = programMonkey.getClass().getMethods();
        for (Method method : methods) {
            LogE("method name : " + method.getName());
        }

        try {
            Method setmLanguageMethod = programMonkey.getClass().getMethod("setmLanguage", String.class);
            setmLanguageMethod.setAccessible(true);

            // 获取返回类型
            Class<?> returnType = setmLanguageMethod.getReturnType();
            LogE("setmLanguage 方法的返回类型 : " + returnType.getName());

            // 获取方法的参数类型列表
            Class<?>[] paramClasses = setmLanguageMethod.getParameterTypes() ;
            for (Class<?> class1 : paramClasses) {
                LogE("setmLanguage 方法的参数类型 : " + class1.getName());
            }

            // 是否是 private 函数，属性是否是 private 也可以使用这种方式判断
            LogE(setmLanguageMethod.getName() + " is private " + Modifier.isPrivate(setmLanguageMethod.getModifiers()));

            // 执行方法
            Object result = setmLanguageMethod.invoke(programMonkey, "Java");
            LogE("setmLanguage 方法的返回结果: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Class<?> getClassObject_1(){
        return ProgramMonkey.class;
    }

    private Class<?> getClassObject_2(){
        ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
        return programMonkey.getClass();
    }

    private Class<?> getClassObject_3(){
        try{
            return Class.forName("com.hansheng.reflect.reflectdeom.ProgramMonkey");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 得到当前类的所有实例
     *
     * */
    private void getCurrentClassFields() {
        ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
        // 获取当前类的所有属性
        Field[] publicFields = programMonkey.getClass().getDeclaredFields();
        for (Field field : publicFields) {
            LogE("declared field name : " + field.getName());
        }

        try {
            // 获取当前类的某个属性
            Field ageField = programMonkey.getClass().getDeclaredField("mAge");
            // 获取属性值
            LogE(" my age is : " + ageField.getInt(programMonkey));
            // 设置属性值
            ageField.set(programMonkey, 10);
            LogE(" my age is : " + ageField.getInt(programMonkey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到当前类和父类的所有公有属性
     *
     * */
    private void getAllFields() {
        ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
        // 得到当前类和父类的所有公有属性
        Field[] publicFields = programMonkey.getClass().getFields();
        for (Field field : publicFields) {
            LogE("field name : " + field.getName());
        }

        try {
            // 获取当前类和父类的某个公有属性
            Field ageField = programMonkey.getClass().getField("mAge");
            LogE(" age is : " + ageField.getInt(programMonkey));
            ageField.set(programMonkey, 8);
            LogE(" my age is : " + ageField.getInt(programMonkey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
