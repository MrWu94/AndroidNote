package com.hansheng.studynote.customview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hansheng.studynote.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hansheng on 16-9-13.
 */

public class PaddingActivity extends AppCompatActivity{
    private final static String TAG=PaddingActivity.class.getSimpleName();
    private ArcProgress arcProgress;
    private NumberProgressBar numberProgressBar;
    private Timer timer;
    private AnimDownloadProgressButton mAnimDownloadProgressButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.padding_layout);
        arcProgress= (ArcProgress) findViewById(R.id.arc_process);
        numberProgressBar= (NumberProgressBar) findViewById(R.id.numberbar1);
        mAnimDownloadProgressButton= (AnimDownloadProgressButton) findViewById(R.id.anim_btn);
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

        mAnimDownloadProgressButton.setCurrentText("安装");
        mAnimDownloadProgressButton.setTextSize(60f);
        mAnimDownloadProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTheButton();
            }
        });
    }
    private void showTheButton() {
        mAnimDownloadProgressButton.setState(AnimDownloadProgressButton.DOWNLOADING);
        mAnimDownloadProgressButton.setProgressText("下载中", mAnimDownloadProgressButton.getProgress() + 8);
        Log.d(TAG, "showTheButton: " + mAnimDownloadProgressButton.getProgress());
        if (mAnimDownloadProgressButton.getProgress() + 10 > 100) {
            mAnimDownloadProgressButton.setState(AnimDownloadProgressButton.INSTALLING);
            mAnimDownloadProgressButton.setCurrentText("安装中");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mAnimDownloadProgressButton.setState(AnimDownloadProgressButton.NORMAL);
                    mAnimDownloadProgressButton.setCurrentText("打开");
                }
            }, 2000);   //2秒
        }
    }
}
