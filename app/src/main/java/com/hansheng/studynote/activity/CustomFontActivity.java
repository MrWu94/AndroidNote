package com.hansheng.studynote.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-10-9.
 */

public class CustomFontActivity extends BaseActivity {
    Typeface typeface;
    private TextView textView;
    private LinearLayout linearLayout;

    @Override
    protected int initContentView() {
        return R.layout.custom_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        typeface = Typeface.createFromAsset(getAssets(), "Ruthie.ttf");
        textView = (TextView) findViewById(R.id.text);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        setTypeface(linearLayout, typeface);


//        textView.setTypeface(typeface);


    }
}
