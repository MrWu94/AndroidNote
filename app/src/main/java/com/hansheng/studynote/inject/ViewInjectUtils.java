package com.hansheng.studynote.inject;


import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Created by hansheng on 2016/7/12.
 */
public class ViewInjectUtils {
    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    public ViewInjectUtils() {
    }

    public static void inject(Activity activity) {
        Log.e("TAG", "inject");
        injectContentView(activity);
        injectViews(activity);
        injectEvents(activity);
    }

    private static void injectEvents(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getMethods();
        //遍历所有的方法
        for (Method method : methods)
        {
            Annotation[] annotations = method.getAnnotations();
            //拿到方法上的所有的注解
            for (Annotation annotation : annotations)
            {
                Class<? extends Annotation> annotationType = annotation
                        .annotationType();
                //拿到注解上的注解
                EventBase eventBaseAnnotation = annotationType
                        .getAnnotation(EventBase.class);
                //如果设置为EventBase
                if (eventBaseAnnotation != null)
                {
                    //取出设置监听器的名称，监听器的类型，调用的方法名
                    String listenerSetter = eventBaseAnnotation
                            .listenerSetter();
                    Class<?> listenerType = eventBaseAnnotation.listenerType();
                    String methodName = eventBaseAnnotation.methodName();

                    try
                    {
                        //拿到Onclick注解中的value方法
                        Method aMethod = annotationType
                                .getDeclaredMethod("value");
                        //取出所有的viewId
                        int[] viewIds = (int[]) aMethod
                                .invoke(annotation, "");
                        //通过InvocationHandler设置代理
                        DynamicHandler handler = new DynamicHandler(activity);
                        handler.addMethod(methodName, method);
                        Object listener = Proxy.newProxyInstance(
                                listenerType.getClassLoader(),
                                new Class<?>[] { listenerType }, handler);
                        //遍历所有的View，设置事件
                        for (int viewId : viewIds)
                        {
                            View view = activity.findViewById(viewId);
                            Method setEventListenerMethod = view.getClass()
                                    .getMethod(listenerSetter, listenerType);
                            setEventListenerMethod.invoke(view, listener);
                        }

                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

    private static void injectViews(Activity activity) {
        Class clazz = activity.getClass();

        Field[] fields = clazz.getDeclaredFields();
        Field[] var6 = fields;
        int var5 = fields.length;

        for (int var4 = 0; var4 < var5; ++var4) {
            Field field = var6[var4];
            Log.e("TAG", field.getName());
            ViewInject viewInjectAnnotation = field.getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null) {
                int viewId = viewInjectAnnotation.value();
                if (viewId != -1) {
                    Log.e("TAG", String.valueOf(viewId));
                }
                try {
                    Method e = clazz.getMethod("findViewById", new Class[]{Integer.TYPE});
                    Object resView = e.invoke(activity, new Object[]{Integer.valueOf(viewId)});
                    field.setAccessible(true);
                    field.set(activity, resView);

                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


        }
    }


    private static void injectContentView(Activity activity) {

        Class clazz = activity.getClass();
        ContentView contentView = (ContentView) clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int contentViewLayoutId = contentView.value();

            try {
                Method e = clazz.getMethod("setContentView", new Class[]{Integer.TYPE});
                e.setAccessible(true);
                e.invoke(activity, new Object[]{Integer.valueOf(contentViewLayoutId)});
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

    }
}
