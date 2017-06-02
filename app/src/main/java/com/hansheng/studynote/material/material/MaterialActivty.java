package com.hansheng.studynote.material.material;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ui.activity.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

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

    private Installation installation;
    private PopupWindow mPopupWindow;

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
        getMacAddress();
        getIPAddress();
        getDeviceId();
        installation = new Installation();
        installation.id(this);

        findViewById(R.id.btn_popuwindow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow();

            }
        });


    }

    private void showPopupWindow() {
        if (mPopupWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.popu_window_content, null);
            mPopupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, 500, true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        }
        mPopupWindow.showAtLocation(findViewById(R.id.btn_float), Gravity.BOTTOM, 0, 0);
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

    private void getMacAddress() {
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String mac = wifiInfo.getMacAddress();
        Log.d(TAG, "getMacAddress: mac=" + mac);
    }

    private void getIPAddress() {
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        Log.d(TAG, "getIPAddress: ip=" + wifiInfo.getIpAddress());
        Log.d(TAG, "getIPAddress: format IP=" + intToIpAddress(wifiInfo.getIpAddress()));
    }

    public static String intToIpAddress(long ipInt) {
        StringBuffer sb = new StringBuffer();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    private void getDeviceId() {
        TelephonyManager telephoneManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Log.d(TAG, "getDeviceId: deviceId=" + telephoneManager.getDeviceId());
    }

    public class Installation {
        private String sID = null;
        private static final String INSTALLATION = "INSTALLATION";

        public synchronized String id(Context context) {
            if (sID == null) {
                File installation = new File(context.getFilesDir(), INSTALLATION);
                try {
                    if (!installation.exists())
                        writeInstallationFile(installation);
                    sID = readInstallationFile(installation);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return sID;
        }

        private String readInstallationFile(File installation) throws IOException {
            RandomAccessFile f = new RandomAccessFile(installation, "r");
            byte[] bytes = new byte[(int) f.length()];
            f.readFully(bytes);
            f.close();
            Log.d(TAG, "readInstallationFile: " + new String(bytes));
            return new String(bytes);
        }

        private void writeInstallationFile(File installation) throws IOException {
            FileOutputStream out = new FileOutputStream(installation);
            String id = UUID.randomUUID().toString();
            out.write(id.getBytes());
            out.close();
        }
    }

}
