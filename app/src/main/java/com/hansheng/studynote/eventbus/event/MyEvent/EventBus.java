package com.hansheng.studynote.eventbus.event.MyEvent;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by hansheng on 2016/7/13.
 */
public class EventBus {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private static EventBus eventBus = new EventBus();

    private static Map<Class, CopyOnWriteArrayList<SubscribeMethod>> mSubscriberMethodByEventType = new HashMap<>();

    private static ThreadLocal<PostingThread> mPostingThread = new ThreadLocal() {
        public PostingThread get() {
            return new PostingThread();
        }
    };

    public static EventBus getInstance() {
        return eventBus;
    }

    private EventBus() {

    }

    public void register(Object subscriber) {
        Class clazz = subscriber.getClass();
        /**
         * 遍历所有方法
         */
        Method[] methods = clazz.getDeclaredMethods();

        CopyOnWriteArrayList subscriberMethods = null;

        Method[] var8 = methods;

        int var7 = methods.length;
        for (int var6 = 0; var6 < var7; ++var6) {
            Method method = var8[var6];
            String methodName = method.getName();
            /**
             * 判断方法是否以onEvent的开头
             */
            if (methodName.startsWith("onEvent")) {
                SubscribeMethod subscribeMethod = null;
                // 方法命中提前在什么线程运行。默认在UI线程
                String threadMode = methodName.substring("onEvent".length());
                ThreadMode mode = ThreadMode.UI;

                Class[] parameterTypes = method.getParameterTypes();
                // 参数的个数为1
                if (parameterTypes.length == 1) {
                    Class eventType = parameterTypes[0];

                    synchronized (this) {
                        if (mSubscriberMethodByEventType.containsKey(eventType)) {
                            subscriberMethods = mSubscriberMethodByEventType.get(eventType);
                        } else {
                            subscriberMethods = new CopyOnWriteArrayList();
                            mSubscriberMethodByEventType.put(eventType, subscriberMethods);
                        }
                    }

                    if (threadMode.equals("Async")) {
                        mode = ThreadMode.Async;
                    }
                    // 提取出method，mode，方法所在类对象，存数的类型封装为SubscribeMethod
                    subscribeMethod = new SubscribeMethod(method, mode, subscriber);
                    subscriberMethods.add(subscribeMethod);
                }

            }
        }
    }

    public void unregister(Object subscriber) {
        Class clazz = subscriber.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        Object subscribeMethods = null;
        Method[] var8 = methods;
        int var7 = methods.length;

        for (int var6 = 0; var6 < var7; ++var6) {

            Method method = var8[var6];
            String methodName = method.getName();

            if (methodName.startsWith("onEvent")) {
                Class[] paramterTypes = method.getExceptionTypes();

                if (paramterTypes.length == 1) {
                    synchronized (this) {
                        mSubscriberMethodByEventType.remove(paramterTypes);
                    }
                }
            }

        }
    }

    public void post(Object eventTypeInstance) {
        //拿到该线程中的PostingThread对象
        PostingThread postingThread = mPostingThread.get();
        postingThread.isMainThread = Looper.getMainLooper() == Looper.myLooper();
        //将事件加入事件队列
        List eventQueue = postingThread.mEventQueue;

        eventQueue.add(eventTypeInstance);

        if (!postingThread.isMainThread) {
            postingThread.isPosting = true;
            while (!eventQueue.isEmpty()) {
                Object eventType = eventQueue.remove(0);
                this.postEvent(eventType, postingThread);
            }
        }
        postingThread.isPosting = false;

    }

    private void postEvent(final Object eventType, PostingThread postingThread) {
        CopyOnWriteArrayList subscribeMethods = null;
        synchronized (this) {
            subscribeMethods = mSubscriberMethodByEventType.get(eventType.getClass());

        }
        Iterator var5 = subscribeMethods.iterator();

        while (var5.hasNext()) {
            final SubscribeMethod subscribeMethod = (SubscribeMethod) var5.next();

            if (subscribeMethod.threadMode == ThreadMode.UI) {
                if (postingThread.isMainThread) {
                    this.invokeMethod(eventType, subscribeMethod);

                } else {
                    this.mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            EventBus.this.invokeMethod(eventType, subscribeMethod);
                        }
                    });
                }
            } else {
                AsyncTask var = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] params) {
                        EventBus.this.invokeMethod(eventType, subscribeMethod);
                        return null;
                    }
                };
            }
        }
    }

    private void invokeMethod(Object eventType, SubscribeMethod subscribeMethod) {
        try {
            subscribeMethod.method.invoke(subscribeMethod.subscriber, new Object[]{eventType});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
