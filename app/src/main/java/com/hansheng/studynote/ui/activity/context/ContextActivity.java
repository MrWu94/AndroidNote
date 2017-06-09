package com.hansheng.studynote.ui.activity.context;

import android.util.Log;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ui.activity.BaseActivity;

/**
 * Created by hansheng on 17-6-8.
 */

public class ContextActivity extends BaseActivity {
    public static final String TAG="ContextActivity";
    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Log.d(TAG, "Component: "+this.getComponentName());
        Log.d(TAG, "CodePath: "+getPackageCodePath());
        Log.d(TAG, "Resource: "+getPackageResourcePath());
        Log.d(TAG, "LocalClass: "+getLocalClassName());
        Log.d(TAG, "TaskId: "+getTaskId());
        Log.d(TAG, "File: "+getFilesDir());


    }
}
