package com.hansheng.studynote.ui.service.screen;

import android.content.Intent;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ui.activity.BaseActivity;

/**
 * Created by hansheng on 17-6-6.
 */

public class ScreenActivity extends BaseActivity {
    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        startService(new Intent(ScreenActivity.this,ScreenService.class));
    }
}
