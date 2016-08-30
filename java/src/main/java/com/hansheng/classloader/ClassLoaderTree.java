package com.hansheng.classloader;

/**
 * Created by hansheng on 16-8-30.
 */

public class ClassLoaderTree {

    public static void main(String... args){
        ClassLoader classLoader=ClassLoaderTree.class.getClassLoader();
        while (classLoader!=null){
            System.out.println(classLoader.toString());
            classLoader.getParent();
        }
    }
}
