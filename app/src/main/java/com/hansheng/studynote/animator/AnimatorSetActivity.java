package com.hansheng.studynote.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/6/23.
 */
public class AnimatorSetActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_set);
        imageView = (ImageView) findViewById(R.id.id_ball);
    }

    public void togetherRun(View view) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView, "sacleY", 1.0f, 2f);
        AnimatorSet ha = new AnimatorSet();
        ha.setDuration(2000);
        ha.setInterpolator(new LinearInterpolator());
        ha.playTogether(anim1,anim2);
        ha.start();
    }

    public void playWithAfter(View view){
        float cx=imageView.getX();
        ObjectAnimator anim1=ObjectAnimator.ofFloat(imageView,"ScaleX",1.0f,2f);
        ObjectAnimator anim2=ObjectAnimator.ofFloat(imageView,"scaleY",1.0f,2f);
        ObjectAnimator anim3=ObjectAnimator.ofFloat(imageView,"x",cx,0f);
        ObjectAnimator anim4=ObjectAnimator.ofFloat(imageView,"x",cx);

        AnimatorSet animSet=new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim3).with(anim4);
        animSet.setDuration(1000);
        animSet.start();

    }

    public void scaleX(View view){

        Animator anim= AnimatorInflater.loadAnimator(this,R.animator.scalex);
        anim.setTarget(imageView);
        anim.start();;
    }

    public void jihe(View view){

        Animator anim=AnimatorInflater.loadAnimator(this,R.animator.together_animator);
        imageView.setPivotX(0);
        imageView.setPivotY(0);
        imageView.invalidate();
        anim.setTarget(imageView);
        anim.start();
    }


}
