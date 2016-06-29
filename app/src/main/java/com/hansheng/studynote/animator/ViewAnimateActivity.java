package com.hansheng.studynote.animator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/6/23.
 */
public class ViewAnimateActivity extends AppCompatActivity {
    private ImageView imageView;
    private float mScreenHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate);

        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenHeight=metrics.heightPixels;
        imageView= (ImageView) findViewById(R.id.id_ball);
    }

    public void viewAnim(View view){
        imageView.animate()
                .alpha(0)
                .y(mScreenHeight/2).setDuration(1000)
                .withStartAction(new Runnable()
                {
                    @Override
                    public void run() {

                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setY(0);
                                imageView.setAlpha(1.0f);
                            }
                        });

                    }
                }).start();
    }
}
