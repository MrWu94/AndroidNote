package com.hansheng.studynote.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.customview.Utils;

/**
 * Created by hansheng on 2016/9/25.
 */

public class ImageActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        imageView= (ImageView) findViewById(R.id.image_view);
        UtilsAnimation.zoomInAndOutAnimation(this,imageView);
    }
}
