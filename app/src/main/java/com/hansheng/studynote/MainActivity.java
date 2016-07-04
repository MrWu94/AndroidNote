package com.hansheng.studynote;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.hansheng.studynote.scroller.SwipeBackFrameLayout;

public class MainActivity extends AppCompatActivity {


//    private View mView;
//    private Runnable mFinishRunnable = new Runnable() {
//        @Override
//        public void run() {
//            finish();
//        }
//    };
    private LinearLayout mainLayout;
    private SwipeBackFrameLayout swipeBackFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout);
//              mainLayout= (LinearLayout) findViewById(R.id.main_layout);
//
//        LayoutInflater layoutInflater=LayoutInflater.from(this);
//        View buttonLayout=layoutInflater.inflate(R.layout.button_layout,null);
//        mainLayout.addView(buttonLayout);



//        swipeBackFrameLayout= (SwipeBackFrameLayout) findViewById(R.id.swipe_back);
//        swipeBackFrameLayout.setCallback(new SwipeBackFrameLayout.Callback() {
//            @Override
//            public void onShouldFinish() {
//                finish();
//                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://mail.google.com"));
//                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
//            }
//        });

    }

//    @Override
//    public void onBackPressed() {
//        mView.postDelayed(mFinishRunnable,5);
//        moveTaskToBack(true);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mView.removeCallbacks(mFinishRunnable);
////    }


    public void onFadeIn(View view) {
        AlphaAnimation animation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.fade_in);
        view.startAnimation(animation);

    }

    public void onFadeOut(View view) {
        AlphaAnimation animation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.fade_out);
        view.startAnimation(animation);
    }

    public void onZoomIn(View view) {
        ScaleAnimation animation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        view.startAnimation(animation);
    }

    public void onZoomOut(View view) {
        ScaleAnimation animation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        view.startAnimation(animation);
    }

    public void onMoveLeft2Right(View view) {
        TranslateAnimation animation = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.move_left_to_right);
        view.startAnimation(animation);
    }

    public void onMoveInFromBottom(View view) {
        TranslateAnimation animation = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.move_in_from_bottom);
        view.startAnimation(animation);
    }

    public void onRotate(View view) {
        RotateAnimation animation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate_to);
        view.startAnimation(animation);
    }

    public void onMoveAndScale(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_and_scale));
    }
}
