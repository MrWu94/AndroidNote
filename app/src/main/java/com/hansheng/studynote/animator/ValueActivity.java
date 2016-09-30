package com.hansheng.studynote.animator;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-30.
 */
public class ValueActivity extends Activity implements View.OnTouchListener {

    private ImageView ivMove;
    private RelativeLayout rlRoot;
    private TextView tvTips;
    private int topTitleHeight;
    private Button animatorBtn1;
    private Button animatorBtn2;
    private Button animatorBtn3;
    private Button animatorBtn4;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_layout);
        ivMove = (ImageView) findViewById(R.id.iv_move);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        tvTips = (TextView) findViewById(R.id.tv_tips);
        animatorBtn1 = (Button) findViewById(R.id.btn_move_animator_1);
        animatorBtn2 = (Button) findViewById(R.id.btn_move_animator_2);
        animatorBtn3 = (Button) findViewById(R.id.btn_move_animator_3);
        animatorBtn4 = (Button) findViewById(R.id.btn_move_animator_4);

        animatorBtn1.setOnClickListener(animatorClickListener);
        animatorBtn2.setOnClickListener(animatorClickListener);
        animatorBtn3.setOnClickListener(animatorClickListener);
        animatorBtn4.setOnClickListener(animatorClickListener);
        rlRoot.setOnTouchListener(this);
        tvTips.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int[] locations = new int[2];
                tvTips.getLocationInWindow(locations);
                topTitleHeight = locations[1];
                break;
            case MotionEvent.ACTION_MOVE:
                moveViewByLayout(ivMove, (int) event.getRawX(),
                        (int) event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    /**
     * 修改view的位置
     *
     * @param view
     * @param rawX
     * @param rawY
     */
    private void moveViewByLayout(View view, int rawX, int rawY) {
        int left = rawX - ivMove.getWidth() / 2;
        int top = rawY - topTitleHeight - ivMove.getHeight() / 2;
        int width = left + view.getWidth();
        int height = top + view.getHeight();
        view.layout(left, top, width, height);
    }

    private View.OnClickListener animatorClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_move_animator_1:
                    moveLinePath();
                    break;
                case R.id.btn_move_animator_2:
                    moveCirclePath();
                    break;
                case R.id.btn_move_animator_3:
                    rotateAndAlpha();
                    break;
                case R.id.btn_move_animator_4:
                    scaleAnimator();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 按直线轨迹运动
     */
    protected void moveLinePath() {
        width = rlRoot.getWidth();
        height = rlRoot.getHeight();
        ValueAnimator xValue = ValueAnimator.ofInt(width / 10, width / 2);
        xValue.setDuration(1000);
        xValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("value"+animation.getAnimatedValue());
                // 轨迹方程 y = 1.5*x
                int x = (Integer) animation.getAnimatedValue();
                int y = (int) (1.5f * x);
                System.out.println("debug:(x,y) = " + x + "," + y);
                moveViewByLayout(ivMove, x, y);
            }
        });
        xValue.setInterpolator(new LinearInterpolator());
        xValue.start();
    }

    /**
     * 先放大后缩小
     */
    protected void scaleAnimator() {
        final float scale = 0.6f;
        AnimatorSet scaleSet = new AnimatorSet();

        ValueAnimator valueAnimatorSmall = ValueAnimator.ofFloat(1.0f, scale);
        valueAnimatorSmall.setDuration(500);

        ValueAnimator valueAnimatorLarge = ValueAnimator.ofFloat(scale, 1.0f);
        valueAnimatorLarge.setDuration(500);

        valueAnimatorSmall.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                System.out.println("scalevalue"+animation.getAnimatedValue());
                float scale = (Float) animation.getAnimatedValue();
                ivMove.setScaleX(scale);
                ivMove.setScaleY(scale);
            }
        });
        valueAnimatorLarge.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (Float) animation.getAnimatedValue();

                ivMove.setScaleX(scale);
                ivMove.setScaleY(scale);
            }
        });

        scaleSet.play(valueAnimatorLarge).after(valueAnimatorSmall);
        scaleSet.start();

    }

    /**
     * 旋转的同时进行透明度变化
     */
    protected void rotateAndAlpha() {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int rotateValue = (Integer) animation.getAnimatedValue();
                ivMove.setRotation(rotateValue);
                float fractionValue = animation.getAnimatedFraction();
                ivMove.setAlpha(fractionValue);
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();
    }

    /**
     * 圆形运行
     */
    protected void moveCirclePath() {
        width = rlRoot.getWidth();
        height = rlRoot.getHeight();
        final int R = width / 4;
        ValueAnimator tValue = ValueAnimator.ofFloat(0,
                (float) (2.0f * Math.PI));
        tValue.setDuration(1000);
        tValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 圆的参数方程 x = R*sin(t) y = R*cos(t)
                float t = (Float) animation.getAnimatedValue();
                int x = (int) (R * Math.sin(t) + width / 2);
                int y = (int) (R * Math.cos(t) + height / 2);
                System.out.println("debug:(x,y) = " + x + "," + y);
                moveViewByLayout(ivMove, x, y);
            }
        });
        tValue.setInterpolator(new DecelerateInterpolator());
        tValue.start();
    }
}