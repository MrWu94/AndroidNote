package com.hansheng.studynote.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-20.
 */

public class VolumnActivity extends AppCompatActivity {
    private VolumnView volumnView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volumn_layout);
        volumnView= (VolumnView) findViewById(R.id.volumn);
        volumnView.setProgress(10);
    }
}
