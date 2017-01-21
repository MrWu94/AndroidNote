package com.hansheng.studynote.Activity.start;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-1-20.
 */

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Launcher);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
