package com.hansheng.studynote.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-18.
 */

public class PregressActivity extends AppCompatActivity {
    private ProgressBarView progressBarView;

    private ProgressDotLineView progressDotLineView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_layout);
        progressBarView= (ProgressBarView) findViewById(R.id.progressbar_view);
        progressDotLineView= (ProgressDotLineView) findViewById(R.id.progress_dotline);

        progressBarView.setDraggingEnabled(true);
        progressBarView.setMax(100);
        progressBarView.setProgress(70);

        progressDotLineView.setMax(100);
        progressDotLineView.setProgress(50);

    }
}
