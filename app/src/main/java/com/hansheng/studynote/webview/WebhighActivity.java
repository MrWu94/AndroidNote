package com.hansheng.studynote.webview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.hansheng.studynote.BuildConfig;
import com.hansheng.studynote.R;

import java.lang.reflect.Field;

/**
 * Created by hansheng on 16-10-22.
 * * 1.js与android的交互
 * 2.android调用js无参无返回值函数
 * 3.android调用js有参无返回值函数
 * 4.android调用js有参有返回值函数（4.4之前）
 * 5.android调用js有参有返回值函数（4.4之后）
 * 独立的web进程，与主进程隔开
 * 这个方法被运用于类似qq，微信这样的超级app中，这也是解决任何webview内存问题屡试不爽的方法
 * 对于封装的webactivity，在manifest.xml中设置
 * <activity android:name=".webview.WebViewActivity" android:launchMode="singleTop" android:process=":remote" android:screenOrientation="unspecified" />
 * 然后在关闭webactivity时销毁进程
 *
 * @Overrideprotected void onDestroy() {
 * super.onDestroy();
 * System.exit(0);
 * }
 *
 */

public class WebhighActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.wb);
        mWebView.getSettings().setJavaScriptEnabled(true);//支持javascript
        mWebView.requestFocus();//触摸焦点起作用
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置允许js弹出alert对话框
        //load本地
        mWebView.loadUrl("file:///android_asset/hellotest.html");
        //load在线
        //mWebView.loadUrl("http://www.google.com");
        //js访问android，定义接口
        mWebView.addJavascriptInterface(new JsInteration(), "control");
        //设置了Alert才会弹出，重新onJsAlert（）方法return true可以自定义处理信息
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //return super.onJsAlert(view, url, message, result);
                Toast.makeText(WebhighActivity.this, message, Toast.LENGTH_LONG).show();
                return true;
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //在当前的webview中跳转到新的url
                view.loadUrl(url);
                //启动手机浏览器来打开新的url
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
                return true;
            }

            //载入页面开始的事件
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            // 载入页面完成的事件
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            //webView默认是不处理https请求的，页面显示空白，需要进行如下设置：
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//如果只是简单的接受所有证书的话，就直接调process()方法就行了
                // handler.cancel();
                // handler.handleMessage(null); } });
            }
        });


    }

    /**
     * android调用js无参无返回值函数
     *
     * @param view
     */
    public void Android2JsNoParmNoResult(View view) {
        final String call = "javascript:sayHello()";
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(call);
            }
        });

    }

    /**
     * android调用js有参无返回值函数
     *
     * @param view
     */
    public void Android2JsHaveParmNoResult(View view) {
        final String call = "javascript:alertMessage(\"" + "我是android传过来的内容,hey man" + "\")";
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(call);
            }
        });
    }

    /**
     * android调用js有参有返回值函数（4.4之前）
     *
     * @param view
     */
    public void Android2JsHaveParmHaveResult(View view) {
        final String call = "javascript:sumToJava(1,2)";
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(call);
            }
        });
    }

    /**
     * android调用js有参有返回值函数（4.4之后）
     * evaluateJavascript方法必须在UI线程（主线程）调用，因此onReceiveValue也执行在主线程
     *
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void Android2JsHaveParmHaveResult2(View view) {
        mWebView.evaluateJavascript("sumToJava2(3,4)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String Str) {
                Toast.makeText(getApplicationContext(), "我是android调用js方法(4.4后)，入参是3和4，js返回结果是" + Str, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 获取网页图片并放大显示
     *
     * @param view
     */
    public void clickPhoto(View view) {
        startActivity(new Intent(this, WithPhotoWebViewActivity.class));
    }

    /**
     * js调用android的方法
     */
    class JsInteration {
        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void onSumResult(int result) {
            Toast.makeText(getApplicationContext(), "我是android调用js方法(4.4前)，入参是1和2，js返回结果是" + result, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 网页回退
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.setVisibility(View.GONE);
            mWebView.removeAllViews();
            mWebView.destroy();
            releaseAllWebViewCallback();
        }
        super.onDestroy();
    }

    /**
     * 防止内存泄露
     */
    public void releaseAllWebViewCallback() {
        if (android.os.Build.VERSION.SDK_INT < 16) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }
}
