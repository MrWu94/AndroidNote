package com.hansheng.studynote.material;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by hansheng on 16-9-22.
 */

public abstract class BaseActivity  extends AppCompatActivity{
    protected abstract void initView();
    protected abstract void initRepository();
}