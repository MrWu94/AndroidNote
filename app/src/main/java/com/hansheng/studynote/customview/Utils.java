package com.hansheng.studynote.customview;

import android.content.res.Resources;

/**
 * Created by hansheng on 16-9-23.
 */
public class Utils {
    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}