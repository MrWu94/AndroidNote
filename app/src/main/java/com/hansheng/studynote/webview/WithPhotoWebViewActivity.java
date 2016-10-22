package com.hansheng.studynote.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-10-22.
 */

public class WithPhotoWebViewActivity extends AppCompatActivity {
    private WebView mWebView;
    private ProgressBar pg;
    private String mShareUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_webview);
        initView();
    }

    private void initView() {
        pg= (ProgressBar) findViewById(R.id.pg);
        pg.setVisibility(View.VISIBLE);
        mWebView = (WebView) findViewById(R.id.wb);
        // 启用javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 找了个带图片的网站
        mWebView.loadUrl("http://m.3gbizhi.com/");
        // 添加js交互接口类，并起别名 imagelistner
        mWebView.addJavascriptInterface(this, "imagelistner");
        mWebView.setWebViewClient(new MyWebViewClient());
        //长按获取图片链接
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                WebView.HitTestResult result= mWebView.getHitTestResult();
                if (result != null) {
                    int type = result.getType();
                    if (type == WebView.HitTestResult.IMAGE_TYPE || type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                        String  imgurl = result.getExtra();
                        Toast.makeText(WithPhotoWebViewActivity.this, imgurl, Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    }

    // js通信接口
    @JavascriptInterface
    public void openImage(String img) {
        System.out.println(img);
       ShowWebViewActivity.startAction(this,img);
        System.out.println(img);
    }

    // 注入js函数监听
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，在还是执行的时候调用本地接口传递url过去
        mWebView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");

    }
    // 监听
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //在当前的webview中跳转到新的url
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            view.getSettings().setJavaScriptEnabled(true);
            pg.setVisibility(View.GONE);
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);

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
        if(mWebView !=null) {
            mWebView.setVisibility(View.GONE);
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        super.onDestroy();
    }


}
