package com.hansheng.studynote.eventbus.event.MyEvent;

import java.lang.reflect.Method;

/**
 * Created by hansheng on 2016/7/13.
 */
public class SubscribeMethod {

    Method method;
    ThreadMode threadMode;
    Object subscriber;


    public SubscribeMethod(Method method,ThreadMode threadMode,  Object subscriber) {
        this.threadMode = threadMode;
        this.method = method;
        this.subscriber = subscriber;
    }
}
