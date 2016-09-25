package com.hansheng.studynote.glide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/9/24.
 */

public class GlideActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_layout);
        imageView= (ImageView) findViewById(R.id.glide_iamgeview);
        Glide.with(this).load("https://avatars0.githubusercontent.com/u/12311938?v=3&s=40")
                .placeholder(R.drawable.ic_launcher)
                .transform(new GlideCircleTransform(this))
                .into(imageView);
    }
}
