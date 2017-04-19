package com.hansheng.studynote.ImageViewandDrawable.SelectorDrawable;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;

import butterknife.Bind;

/**
 * Created by hansheng on 17-1-12.
 */

public class SelectorActivity extends BaseActivity {

    @Bind(R.id.btn_selected)
    SelectorButton mButton;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {


        Drawable normalDrawable = getResources().getDrawable(R.drawable.exam_list_bg_default);
        Drawable pressedDrawable = getResources().getDrawable(R.drawable.exam_list_bg_pressed);
        mButton.setSelecorDrawable(normalDrawable, pressedDrawable);

        // // mButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.d));;
        // StateListDrawable seletor = new StateListDrawable();
        // seletor.addState(new int[] { android.R.attr.state_pressed },
        // getResources().getDrawable(R.drawable.btn_login_normal));
        // seletor.addState(new int[] { android.R.attr.state_focused },
        // getResources().getDrawable(R.drawable.btn_login_normal));
        // seletor.addState(new int[] {}, getResources().getDrawable(R.drawable.btn_login_pressed));
        // mButton.setBackgroundDrawable(seletor);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ľăť÷");
            }
        });

    }
}
