package com.hanshenghanshengeventgbus.bus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hansheng on 16-8-24.
 */

public class Helper {



    private static boolean shouleSkipClass(final Class<?> clazz){
        final String clsName=clazz.getName();
        return Object.class.equals(clazz)
                || clsName.startsWith("java.")
                || clsName.startsWith("javax.")
                || clsName.startsWith("android.")
                || clsName.startsWith("com.android.");
    }

    private static boolean isAnnotatedMethod(final Method method,final Class<? extends Annotation> annotation ){
        // must has annotation
        if (!method.isAnnotationPresent(annotation)) {
            return false;
        }
        // must be public
        if (!Modifier.isPublic(method.getModifiers())) {
            return false;
        }
        // must not static
        if (Modifier.isStatic(method.getModifiers())) {
            return false;
        }
        // must not be volatile
        // fix getDeclaredMethods bug, if method in base class,
        // it returns duplicate method,
        // one is normal, the other is the same but with volatile modifier
        if (Modifier.isVolatile(method.getModifiers())) {
            return false;
        }
        // must has only one parameter
        if (method.getParameterTypes().length != 1) {
            return false;
        }

        return true;
    }

    public static Set<Method> findAnnotionedMethods(final Class<?> type, final Class<? extends Annotation> annotion){
       Class<?> clazz=type;
        final Set<Method> methods=new HashSet<Method>();
        while (!shouleSkipClass(clazz)){
            final Method[] allMethods=clazz.getDeclaredMethods();
            for(final Method method:allMethods){
                if(isAnnotatedMethod(method,annotion)){
                    methods.add(method);
                }
            }
            clazz.getSuperclass();
        }
        return methods;


    }
}
