package com.hansheng.studynote.animator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.hansheng.studynote.activity.BaseActivity;
import com.hansheng.studynote.R;

import butterknife.Bind;

/**
 * Created by hansheng on 2016/6/23.
 */
public class ValueAnimatoeActivity extends BaseActivity {

    private float mScreenHeight;


    @Bind(R.id.bol_blue)
    ImageView imageView;

    @Override
    protected int initContentView() {
        return R.layout.value_animator;
    }

    @Override
    protected void initView() {

    }
    public void verticalRun(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, view.getHeight(), imageView.getHeight());
        animator.setTarget(imageView);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageView.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }

    public void paowuxian(View view) {

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                //fraction已执行时间占总时间百分比
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                imageView.setX(point.x);
                imageView.setY(point.y);

            }
        });
    }

    public void fadeut(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(imageView, "alpha", 0.5f);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup parent = (ViewGroup) imageView.getParent();
                if (parent != null) {
                    parent.removeView(imageView);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }
}
