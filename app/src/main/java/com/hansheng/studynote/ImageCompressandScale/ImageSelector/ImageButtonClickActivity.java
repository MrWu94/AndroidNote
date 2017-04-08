package com.hansheng.studynote.ImageCompressandScale.ImageSelector;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-4-8.
 */

public class ImageButtonClickActivity extends AppCompatActivity {
    private ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_selector);
        imageButton = (ImageButton) findViewById(R.id.image_select);

//        ColorFilter filter = new LightingColorFilter
//                (getResources().getColor(R.color.primary), getResources().getColor(R.color.primary));
//
//        ImageButtonClickUtils.setClickState(imageButton, R.drawable.ic_dialog_photo_continue_normal, R.drawable.ic_dialog_photo_continue_pressed);
        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if(action == MotionEvent.ACTION_DOWN){
                    ((ImageButton)v).setColorFilter(getResources().getColor(R.color.grid));
                }else if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL){
                    ((ImageButton)v).clearColorFilter();
                }

                // 为了不影响监听按钮的onClick回调，返回值应为false
                return false;
            }
        });
    }
}
