package com.hansheng.studynote.ui.widget;

import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-21.
 */

public class BActivity extends AppCompatActivity {
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // https://crazygui.wordpress.com/2010/09/05/high-quality-radial-gradient-in-android/
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        // int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        Window window = getWindow();
        GradientDrawable gradientBackgroundDrawable = GradientUtils.create(
                ContextCompat.getColor(this, R.color.mp_theme_dark_blue_gradientColor),
                ContextCompat.getColor(this, R.color.mp_theme_dark_blue_background),
                screenHeight / 2, // (int) Math.hypot(screenWidth / 2, screenHeight / 2),
                0.5f,
                0.5f
        );
        window.setBackgroundDrawable(gradientBackgroundDrawable);
        window.setFormat(PixelFormat.RGBA_8888);
    }
}
