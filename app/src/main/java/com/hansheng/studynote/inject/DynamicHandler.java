package com.hansheng.studynote.inject;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by hansheng on 2016/7/12.
 */
public class DynamicHandler implements InvocationHandler {
    private WeakReference<Object> handlerRef;
    private final HashMap<String, Method> methodMap = new HashMap(1);

    public DynamicHandler(Object handler) {
        this.handlerRef = new WeakReference(handler);
    }

    public void addMethod(String name, Method method) {
        this.methodMap.put(name, method);
    }

    public Object getHandler() {
        return this.handlerRef.get();
    }

    public void setHandler(Object handler) {
        this.handlerRef = new WeakReference(handler);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object handler = this.handlerRef.get();
        if(handler != null) {
            String methodName = method.getName();
            method = (Method)this.methodMap.get(methodName);
            if(method != null) {
                return method.invoke(handler, args);
            }
        }

        return null;
    }
}
