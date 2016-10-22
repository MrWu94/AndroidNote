package com.hansheng.studynote.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by hansheng on 16-10-22.
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
