package com.hanshenghanshengeventgbus.bus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Created by hansheng on 16-8-24.
 */

public class Bus {

    private static class SingletonHolder{
        static final Bus INSTANCE=new Bus();
    }

    public static Bus getDefault(){
        return SingletonHolder.INSTANCE;
    }

    private Map<Object,Set<Method>>  mMethodMap=new WeakHashMap<Object,Set<Method>>();


    public void register(final Object target){
        if(mMethodMap.containsKey(target)){
            System.out.println("target"+target+"is already registered");
            return;
        }
        Set<Method> methods=Helper.findAnnotionedMethods(target.getClass(),BusReceiver.class);
        if(methods==null||methods.isEmpty()){
            return;
        }
        mMethodMap.put(target,methods);
    }
    public void unregister(final Object target){

        System.out.println("unregister() target=" + target);
        mMethodMap.remove(target);
    }
    public void post(Object event){
        final Class<?> eventClass=event.getClass();
        int sentCount = 0;
        for(Map.Entry<Object,Set<Method>> entry:mMethodMap.entrySet()){
            final Object target=entry.getKey();
            final Set<Method> methods=entry.getValue();
            if(methods==null||methods.isEmpty()){
                continue;
            }
            for(Method method:methods){
                Class<?> parammeterClass=method.getParameterTypes()[0];
                if(parammeterClass.isAssignableFrom(eventClass)){
                    System.out.println("post event to"+target+"."+method.getName());
                    try {
                        method.invoke(target,event);
                        sentCount++;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(sentCount==0){
                System.out.println("no receiver found for event"+event);
            }
        }

    }
}
