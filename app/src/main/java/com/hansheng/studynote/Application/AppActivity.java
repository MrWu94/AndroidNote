package com.hansheng.studynote.Application;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hansheng.studynote.R;
import com.hansheng.studynote.StudyApplication;

/**
 * Created by hansheng on 16-10-24.
 * Context数量 = Activity数量 + Service数量 + 1
 * getApplication()方法的语义性非常强，一看就知道是用来获取Application实例的，但是这个方法只有在Activity和
 * Service中才能调用的到。那么也许在绝大多数情况下我们都是在Activity或者Service中使用Application的，
 * 但是如果在一些其它的场景，比如BroadcastReceiver中也想获得Application的实例，这时就可以借助getApplicationContext()方法了
 * getBaseContext()方法得到的是一个ContextImpl对象
 */

public class AppActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item);
        StudyApplication myApp = (StudyApplication) getApplication();

        Log.d("TAG", "getApplication is " + myApp);
        Context appContext = getApplicationContext();
        Log.d("TAG", "getApplicationContext is " + appContext);
        ApplicationInfo appinfo=getApplicationInfo();
        Log.d("TAG", "getApplicationInfo is " + appinfo);

    }
}
