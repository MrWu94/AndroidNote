package com.hansheng.studynote.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hansheng on 16-9-13.
 */

public class PaddingActivity extends AppCompatActivity{
    private ArcProgress arcProgress;
    private NumberProgressBar numberProgressBar;
    private Timer timer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.padding_layout);
        arcProgress= (ArcProgress) findViewById(R.id.arc_process);
        numberProgressBar= (NumberProgressBar) findViewById(R.id.numberbar1);
        arcProgress.setProgress(50);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        numberProgressBar.incrementProgressBy(1);
                    }
                });
            }
        }, 1000, 100);
    }
}
