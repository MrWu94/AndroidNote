package com.hansheng.studynote.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-10-22.
 * WebView的方法
 * 前进、后退
 * <p>
 * goBack()//后退
 * goForward()//前进
 * goBackOrForward(intsteps) //以当前的index为起始点前进或者后退到历史记录中指定的steps，
 * 如果steps为负数则为后退，正数则为前进
 * <p>
 * canGoForward()//是否可以前进
 * canGoBack() //是否可以后退
 * <p>
 * 文／Wing_Li（简书作者）
 * 原文链接：http://www.jianshu.com/p/3fcf8ba18d7f
 * 著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。
 */

public class ShowWebViewActivity extends AppCompatActivity {
    private TextView imageTextView = null;
    private ImageView img;
    private String imagePath = null;

    public static void startAction(Context context, String image) {
        Intent intent = new Intent(context, ShowWebViewActivity.class);
        intent.putExtra("image", image);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_big_photo);
        this.imagePath = getIntent().getStringExtra("image");

        this.imageTextView = (TextView) findViewById(R.id.tv_url);
        img = (ImageView) findViewById(R.id.img);

        imageTextView.setText(this.imagePath);
        Glide.with(this).load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f).into(img);
    }

}