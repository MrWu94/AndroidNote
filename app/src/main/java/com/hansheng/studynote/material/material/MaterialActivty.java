package com.hansheng.studynote.material.material;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ui.activity.BaseActivity;

/**
 * Created by hansheng on 17-5-19.
 */

public class MaterialActivty extends BaseActivity {
    private FloatingActionButton floatButton;
    @Override
    protected int initContentView() {
        return R.layout.activiity_material;
    }

    @Override
    protected void initView() {
        floatButton= (FloatingActionButton) findViewById(R.id.btn_float);
        findViewById(R.id.btn_float).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(floatButton, "这是错误操作!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }
}
