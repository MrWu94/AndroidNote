package com.hansheng.studynote.diapatchTouchEvent.disallow;

import android.view.MotionEvent;
import android.view.View;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ui.activity.BaseActivity;

/**
 * Created by hansheng on 17-6-1.
 */

public class DisallowActivity extends BaseActivity implements View.OnTouchListener {
    public static final String TAG = "DisallowActivity";

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
        }
        return false;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }



}
