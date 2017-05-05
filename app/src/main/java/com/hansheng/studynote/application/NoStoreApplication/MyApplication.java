package com.hansheng.studynote.application.NoStoreApplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by hansheng on 17-3-2.
 * <p>
 * http://zmywly8866.github.io/2014/12/26/android-do-not-store-data-in-the-application-object.html
 *   不要在Application对象中缓存数据化，这有可能会导致你的程序崩掉。
 * 请使用Intent在各组件之间传递数据，抑或是将数据存储在磁盘中,然后在需要的时候取出来。
 * <p>
 * 为什么会这样？
 *   在上面这个例子中，程序之所以会崩溃掉是因为恢复之后APP的Application对象是全新的，所以缓存在Application中的用户名成员变量为空值，
 * 在程序调用String的toUpperCase()方法时由于NullPointerException而崩溃掉。
 * <p>
 *   导致这个问题的主要原因是：Application对象并不是始终在内存中的，它有可能会由于系统内存不足而被杀掉。
 * 但Android在你恢复这个应用时并不是重新开始启动这个应用，它会创建一个新的Application对象并且启动上次用户离开时的activity
 * 以造成这个app从来没有被kill掉得假象。
 * <p>
 *   我们以为可以通过Application来缓存数据，却没想到恢复APP时直接跑了B Activity而不是先启动A Activity，最终导致的结果是程序意外的崩溃掉了。
 */
public class MyApplication extends Application {

    public MyApplication getInstance() {
        return instance;
    }

    public MyApplication instance;

    String name;

    private static Context context;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }
}