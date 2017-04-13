package com.hansheng.studynote.AndroidIcon;

import android.content.Context;

/**
 * Created by hansheng on 17-4-13.
 */

public class DimenUtils {
    public DimenUtils() {
    }

    public static int dp2px(Context context, float dpValue) {
        return (int)(dpValue * context.getResources().getDisplayMetrics().density + 0.5F);
    }

    public static int px2dp(Context context, float pxValue) {
        return (int)(pxValue / context.getResources().getDisplayMetrics().density + 0.5F);
    }
}
