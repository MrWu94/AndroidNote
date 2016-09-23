package com.hansheng.studynote.material;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-22.
 */

public class NewsActivity extends BaseActivity {

    private WebView webView;
    private ImageView titleImageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    public static final String NEWS = "news_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        initRepository();
    }
    @Override
    protected void initView() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        titleImageView = (ImageView) findViewById(R.id.ivImage);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        collapsingToolbarLayout.setTitle("NBA");
    }

    @Override
    protected void initRepository() {}

}
