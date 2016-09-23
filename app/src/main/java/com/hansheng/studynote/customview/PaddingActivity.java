package com.hansheng.studynote.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-13.
 */

public class PaddingActivity extends AppCompatActivity{
    private ArcProgress arcProgress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.padding_layout);
        arcProgress= (ArcProgress) findViewById(R.id.arc_process);
        arcProgress.setProgress(50);
    }
}
