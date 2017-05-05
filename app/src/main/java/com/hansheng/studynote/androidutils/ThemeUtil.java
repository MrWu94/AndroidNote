package com.hansheng.studynote.androidutils;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import com.hansheng.studynote.R;


public class ThemeUtil {

    private ThemeUtil() {}

    @ColorInt public static int getPrimaryColor(@NonNull Context context) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, value, true);
        return value.data;
    }

    @ColorInt public static int getThemeAccentColor(@NonNull Context context) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        return value.data;
    }

    public static boolean isTextSizeFromResources(@NonNull Context context, int resId) {
        try {
            return context.getResources().getDimension(resId) != 0;
        } catch (Exception ignored) {}
        return false;
    }

}
