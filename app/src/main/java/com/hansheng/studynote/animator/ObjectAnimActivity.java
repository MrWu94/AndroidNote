package com.hansheng.studynote.animator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/6/23.
 */
public class ObjectAnimActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_animator);
    }
    public void rotate(final View view){
        //ofFloat()方法的第二个参数到底可以传哪些值呢？我们使用过了alpha、rotation、translationX和scaleY这几个值,
        // 分别可以完成淡入淡出、旋转、水平移动、垂直缩放
        ObjectAnimator anim=ObjectAnimator.ofFloat(view,"rotationX",1.0f,0.0f)
                .setDuration(500);
        ObjectAnimator anim1=ObjectAnimator.ofFloat(view,"alpha",1f,0f,1f).setDuration(1000);
        ObjectAnimator anim2=ObjectAnimator.ofFloat(view,"scalY",1f,3f,1f).setDuration(1000);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float val= (float) animation.getAnimatedValue();
                view.setAlpha(val);
                view.setScaleX(val);
                view.setScaleY(val);
            }
        });

    }

}
