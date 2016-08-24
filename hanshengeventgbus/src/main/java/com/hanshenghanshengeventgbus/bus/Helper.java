package com.hanshenghanshengeventgbus.bus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 16-8-24.
 */

public class Helper {

    public static List<Method> findAnnotionedMethods(final Class<?> type, final Class<? extends Annotation> annotion){
        final List<Method> methods=new ArrayList<Method>();
        Method[] ms=type.getDeclaredMethods();
        for(Method method:ms){
            if(Modifier.isStatic(method.getModifiers())){
                continue;
            }
            // must be public
            if (!Modifier.isPublic(method.getModifiers())) {
                continue;
            }
            // must has only one parameter
            if (method.getParameterTypes().length != 1) {
                continue;
            }
            if(!method.isAnnotationPresent(annotion)){
                continue;
            }
            methods.add(method);
        }
        return methods;


    }
}
