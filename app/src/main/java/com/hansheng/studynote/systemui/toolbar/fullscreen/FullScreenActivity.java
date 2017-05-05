package com.hansheng.studynote.systemui.toolbar.fullscreen;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-4-25.
 */

public class FullScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_main);
//        if (Build.VERSION.SDK_INT >= 21) {
//        我们触摸屏幕的任意位置都会退出全屏
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
//            decorView.setSystemUiVisibility(option);
//           /* getWindow().setNavigationBarColor(Color.TRANSPARENT);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);*/
//        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        /**
         *
         View decorView = getWindow().getDecorView();
         int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
         | View.SYSTEM_UI_FLAG_FULLSCREEN;
         decorView.setSystemUiVisibility(option);*/

        /**
         * 使用了SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION，表示会让应用的主体内容占用系统导航栏的空间，
         * 然后又调用了setNavigationBarColor()方法将导航栏设置成透明色
         if (Build.VERSION.SDK_INT > 21) {
         View decorView = getWindow().getDecorView();
         int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
         | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
         | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
         decorView.setSystemUiVisibility(option);
         getWindow().setNavigationBarColor(Color.TRANSPARENT);
         getWindow().setStatusBarColor(Color.TRANSPARENT);

         }*/
//        真正的沉浸式模式
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
