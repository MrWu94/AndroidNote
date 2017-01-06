package com.hansheng.studynote.animator;


import android.view.View;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;

import butterknife.OnClick;

/**
 * Created by hansheng on 17-1-6.
 */

public class AnimatorActivity extends BaseActivity {
    @Override
    protected int initContentView() {
        return R.layout.activity_animator;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.show_amimatorset, R.id.show_objectamimator, R.id.show_valueanimator, R.id.show_valueanimator1, R.id.show_viewanimation, R.id.show_xfermode})
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
        }
    }
}
