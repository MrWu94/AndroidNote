package com.hansheng.studynote.inject;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hansheng on 2016/7/12.
 */
@Target(ElementType.METHOD)
@EventBase(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener", methodName = "onClick")
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {

    int[] value();
}
