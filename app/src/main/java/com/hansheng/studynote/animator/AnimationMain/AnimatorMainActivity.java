package com.hansheng.studynote.animator.AnimationMain;


import android.view.View;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;
import com.hansheng.studynote.animator.AnimatorSetActivity;
import com.hansheng.studynote.animator.ObjectAnimActivity;
import com.hansheng.studynote.animator.ValueActivity;
import com.hansheng.studynote.animator.ValueAnimatoeActivity;
import com.hansheng.studynote.animator.ViewAnimateActivity;
import com.hansheng.studynote.animator.XfermodeActivity;
import com.hansheng.studynote.animator.animationdrawable.AminationDrawableActivity;
import com.hansheng.studynote.animator.higeanim.HighAnimActivity;
import com.hansheng.studynote.ImageCompressandScale.drawable.AnimationDrawableActivity1;

import butterknife.OnClick;

/**
 * Created by hansheng on 17-1-6.
 */

public class AnimatorMainActivity extends BaseActivity {
    @Override
    protected int initContentView() {
        return R.layout.activity_animator;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.show_amimatorset, R.id.show_objectamimator, R.id.show_valueanimator,
            R.id.show_valueanimator1, R.id.show_viewanimation, R.id.show_xfermode,
            R.id.show_high_anim,R.id.show_animation_drawable,R.id.show_animation_drawable1})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.show_amimatorset:
                setIntent(AnimatorSetActivity.class);
                break;
            case R.id.show_objectamimator:
                setIntent(ObjectAnimActivity.class);
                break;
            case R.id.show_valueanimator:
                setIntent(ValueActivity.class);
                break;
            case R.id.show_valueanimator1:
                setIntent(ViewAnimateActivity.class);
                break;
            case R.id.show_viewanimation:
                setIntent(ValueAnimatoeActivity.class);
                break;
            case R.id.show_xfermode:
                setIntent(XfermodeActivity.class);
                break;
            case R.id.show_high_anim:
                setIntent(HighAnimActivity.class);
                break;
            case R.id.show_animation_drawable:
                setIntent(AminationDrawableActivity.class);
                break;
            case R.id.show_animation_drawable1:
                setIntent(AnimationDrawableActivity1.class);
                break;
        }
    }
}
