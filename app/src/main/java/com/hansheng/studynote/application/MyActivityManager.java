package com.hansheng.studynote.application;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by mrwu on 2017/6/20.
 * <p>
 * 关于获取当前Activity的一些思考
 *
 * 在Android开发过程中，我们有时候需要获取当前的Activity实例，比如弹出Dialog操作，必须要用到这个。
 * 关于如何实现由很多种思路，这其中有的简单，有的复杂，这里简单总结一下个人的一些经验吧。
 *
 * 获取当去actiivty的实例
 * 回调方法
 * 介绍了上面两种不是尽善尽美的方法，这里实际上还是有一种更便捷的方法，那就是通过Framework提供的回调来实现。
 * Android自 API 14开始引入了一个方法，即Application的registerActivityLifecycleCallbacks方法，
 * 用来监听所有Activity的生命周期回调，比如onActivityCreated,onActivityResumed等。
 * So，一个简单的实现如下
 */

public class MyActivityManager {

    private static MyActivityManager sInstance = new MyActivityManager();
    private WeakReference<Activity> sCurrentActivityWeakRef;


    private MyActivityManager() {

    }

    public static MyActivityManager getInstance() {
        return sInstance;
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }
}
