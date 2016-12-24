package com.hansheng.studynote.Activity.SystemServiceMemory;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hansheng.studynote.R;
import com.hansheng.studynote.StudyApplication;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by hansheng on 16-12-19.
 * 如何解决
 * <p>
 * 不使用静态持有PowerManager
 * <p>
 * 因为static是一个很容易和内存泄漏产生关联的因素
 * <p>
 * static变量与类的生命周期相同
 * 类的生命周期等同于类加载器
 * 类加载器通常和进程的生命周期一致
 * 所以通过去除static可以保证变量周期和Activity实例相同。这样就不会产生内存泄漏问题。
 * <p>
 * 使用ApplicationContext
 */

public class SystemServiceMemoryActivity extends AppCompatActivity {
    private static PowerManager powerManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_main);
        //会引起内存泄露
//        powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        powerManager = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
//        showSystemAlertDialog();

    }

    private void showSystemAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getApplicationContext());
        dialogBuilder.setTitle("Title");
        dialogBuilder.setMessage("Use Application Context");
        AlertDialog dialog = dialogBuilder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 在Activity的onDestroy()里添加如下代码即可对该Activity的内存泄露问题进行监控。
         * 一般我们的项目都会有一个BaseActivity,在BaseActivity里onDestroy()方法里添加如下代码即可监控该App所有的Activity的内存泄露问题。
         */
        RefWatcher refWatcher = StudyApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
