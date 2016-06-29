package com.hansheng.studynote.animator.higeanim;

import android.animation.TimeInterpolator;

/**
 * Created by hansheng on 2016/6/23.
 */
public class DecelerateAccelerateInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result;
        if(input<=0.5){
            result= (float) (Math.sin(Math.PI*input)/2);
        }else{
            result= (float) (2-Math.sin(Math.PI*input)/2);
        }
        return result;
    }
}
