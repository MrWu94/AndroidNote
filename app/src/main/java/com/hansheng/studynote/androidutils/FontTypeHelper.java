package com.hansheng.studynote.androidutils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;


public class FontTypeHelper {


    private static final Map<String, Typeface> cacheTypefaces = new LinkedHashMap<>();

    public static Typeface get(@NonNull Context c, @Nullable String typeFacePath) {
        if (TextUtils.isEmpty(typeFacePath)) return null;
        synchronized (cacheTypefaces) {
            if (!cacheTypefaces.containsKey(typeFacePath)) {
                Typeface t = Typeface.createFromAsset(c.getAssets(), typeFacePath);
                cacheTypefaces.put(typeFacePath, t);
            }
            return cacheTypefaces.get(typeFacePath);
        }
    }

    public static void setTextTypeFace(@NonNull TextView textView, @Nullable String typeFacePath) {
        Typeface typeface = get(textView.getContext(), typeFacePath);
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
    }

    public static void clear() {
        cacheTypefaces.clear();
    }
}
