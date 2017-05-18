package com.hansheng.studynote.material.materialdesign;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-15.
 * Android使用fitsSystemWindows属性实现--状态栏【status_bar】各版本适配方案
 * 透明statusbar和全屏ImageView http://www.jianshu.com/p/5c7f7aeae978
 * status bar设置成为透明颜色.
 * <p>
 * <style name="AppTheme.NoStatusBar">
 * <item name="android:windowTranslucentStatus">true</item>
 * </style>
 * 页面的根布局是CollapsingToolbarLayout.
 * <p>
 * <android.support.design.widget.CollapsingToolbarLayout
 * xmlns:android="http://schemas.android.com/apk/res/android"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * android:fitsSystemWindows="true">
 * <p>
 * <ImageView
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * android:contentDescription="@null"
 * android:fitsSystemWindows="true"
 * android:scaleType="centerCrop"
 * android:src="@drawable/christmas"/>
 * <p>
 * </android.support.design.widget.CollapsingToolbarLayout>
 * <p>
 * 文／SpikeKing（简书作者）
 * 原文链接：http://www.jianshu.com/p/5c7f7aeae978
 * 著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/1122/3712.html
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        AppManager.getAppManager().addActivity(this);

        SetStatusBarColor();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

}
