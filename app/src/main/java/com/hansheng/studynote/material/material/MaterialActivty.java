package com.hansheng.studynote.material.material;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ui.activity.BaseActivity;

import butterknife.Bind;

/**
 * Created by hansheng on 17-5-19.
 */

public class MaterialActivty extends BaseActivity {
    public static final String TAG = "MaterialActivty";
    private FloatingActionButton floatButton;
    private Toast toast;
    @Bind(R.id.tv_error)
    TextView tvError;
    @Bind(R.id.ed_login)
    EditText editText;

    @Override
    protected int initContentView() {
        return R.layout.activiity_material;
    }

    @Override
    protected void initView() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {

            View decorView = getWindow().getDecorView();

            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }


        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        floatButton = (FloatingActionButton) findViewById(R.id.btn_float);
        findViewById(R.id.btn_float).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(floatButton, "这是错误操作!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                myToolbar.setVisibility(View.VISIBLE);
            }
        });
        toast = Toast.makeText(this, "确定要退出吗？", Toast.LENGTH_SHORT);
        tvError.setText(DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_TIME));
//        editText.setError("输入错误");
//        SystemClock.sleep() 这个方法在保证一定时间的 sleep 时很方便，通常我用来进行 debug 和模拟网络延时。

        getPixel(this);
        getNavigationBarHeight(this);
        getStatusBarHeight(this);
        SystemClock.sleep(1);

        caculateMemory();

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.d(TAG, "onUserLeaveHint: ");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            quitToast();
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        quitToast();
//        quit();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.d(TAG, "onUserInteraction: ");
    }

    private void quitToast() {
        if (null == toast.getView().getParent()) {
            toast.show();
        } else {
            System.exit(0);
        }
    }

    private void getPixel(Context context) {
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        int width = display.widthPixels;
        int height = display.heightPixels;
        Log.d(TAG, "wifth: " + width + "height=" + height);
    }


    private int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceHeight = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int resourceWidth = resources.getIdentifier("navigation_bar_width", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceHeight);
        int width = resources.getDimensionPixelOffset(resourceWidth);
        Log.d(TAG, "getNavigationBarHeight: height=" + height);
        Log.d(TAG, "getNavigationBarHeight: width=" + width);
        return height;
    }

    private int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
//        int resourceWidth = resources.getIdentifier("status_bar_width", "dimen", "android");
//        int width = resources.getDimensionPixelOffset(resourceWidth);
        int height = resources.getDimensionPixelSize(resourceId);
        Log.d(TAG, "getStatusBarHeight: height=" + height);
//        Log.d(TAG, "getStatusBarHeight: width=" + width);
        return height;
    }


    private void quit() {
        android.os.Process.killProcess(android.os.Process.myPid());
//        finish();
        System.exit(0);
    }
    private void caculateMemory() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        Log.i(TAG, "系统剩余内存:" + (info.availMem >> 10) + "k");
        Log.i(TAG, "系统是否处于低内存运行：" + info.lowMemory);
        Log.i(TAG, "当系统剩余内存低于" + (info.threshold >> 10) + "k" + "时就看成低内存运行");
    }

}
