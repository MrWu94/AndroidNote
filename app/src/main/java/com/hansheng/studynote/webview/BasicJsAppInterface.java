package com.hansheng.studynote.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by hansheng on 16-10-22.
 * 避免WebView内存泄露的一些方式
 * 1.可以将 Webview 的 Activity 新起一个进程，结束的时候直接System.exit(0);退出当前进程；
 * 启动新进程，主要代码： AndroidManifest.xml 配置文件代码如下
 * <p>
 * <activity
 * android:name=".ui.activity.Html5Activity"
 * android:process=":lyl.boon.process.web">
 * <intent-filter>
 * <action android:name="com.lyl.boon.ui.activity.htmlactivity"/>
 * <category android:name="android.intent.category.DEFAULT"/>
 * </intent-filter>
 * </activity>
 * 在新进程中启动 Activity ，里面传了 一个 Url：
 * <p>
 * Intent intent = new Intent("com.lyl.boon.ui.activity.htmlactivity");
 * Bundle bundle = new Bundle();
 * bundle.putString("url", gankDataEntity.getUrl());
 * intent.putExtra("bundle",bundle);
 * startActivity(intent);
 * 然后在 Html5Activity 的onDestory() 最后加上 System.exit(0); 杀死当前进程。
 */

public class BasicJsAppInterface {
    private Context cxt;

    public BasicJsAppInterface(Context cxt) {
        this.cxt = cxt;
    }

    //如果targetSDKVersion设置为17以上,这里需要添加该annotation标志
    @JavascriptInterface
    public void showToast() {
        Toast.makeText(this.cxt, "toast in android", Toast.LENGTH_SHORT).show();
    }
}
