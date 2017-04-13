package com.hansheng.studynote.ImageCompressandScale.animationdrawable;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-4-11.
 */

public class AminationDrawableActivity extends AppCompatActivity {

    ImageView imageView;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_drawable_main);
        imageView = (ImageView) findViewById(R.id.iv_welcome_main);

        animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        freeAnimationDrawable(animationDrawable);
    }

    /**
     * 释放AnimationDrawable占用的内存
     *
     *
     * */
    @SuppressWarnings("unused")
    private void freeAnimationDrawable(AnimationDrawable animationDrawable) {
        animationDrawable.stop();
        for (int i = 0; i < animationDrawable.getNumberOfFrames(); ++i){
            Drawable frame = animationDrawable.getFrame(i);
            if (frame instanceof BitmapDrawable) {
                ((BitmapDrawable)frame).getBitmap().recycle();
            }
            frame.setCallback(null);
        }

        animationDrawable.setCallback(null);
    }
}
