package com.hansheng.studynote.Activity.Storage;

import android.os.Environment;
import android.util.Log;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-1-20.
 */

public class EnvironmentActivity extends BaseActivity {
    private static final String TAG="EnvironmentActivity";
    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        Log.d(TAG, "ExternalStorageDirectory: "+Environment.getExternalStorageDirectory());
        Log.d(TAG, "ExternalStorageDirectory.getPath(): "+Environment.getExternalStorageDirectory().getPath());

        Log.d(TAG, "ExternalStorageState(): "+Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));

        Log.d(TAG, "ExternalStorageState(): "+Environment.getExternalStorageState());

        Log.d(TAG, "getCacheDir: "+this.getCacheDir());

        Log.d(TAG, "ExternalCacheDir: "+this.getExternalCacheDir());

    }
}
