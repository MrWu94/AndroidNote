package com.hansheng.studynote.ImageViewandDrawable.drawable;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by wfq on 2016/11/12.
 */

public class AnimationDrawableActivity1 extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.animationdrawable_main);
        imageView = (ImageView) findViewById(R.id.imageView_animation1);
        imageView.setBackgroundResource(R.drawable.ic_launcher);

    }
    public void myClickHandler(View targetButton){
        // 获取AnimationDrawable对象
        AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getBackground();

        // 动画是否正在运行
        if(animationDrawable.isRunning()){
            //停止动画播放
            animationDrawable.stop();
        }
        else{
            //开始或者继续动画播放
            animationDrawable.start();
        }


    }
}
