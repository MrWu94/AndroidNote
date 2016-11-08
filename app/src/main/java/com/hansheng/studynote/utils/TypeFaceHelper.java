package com.hansheng.studynote.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

/**
 * Created by hansheng on 16-11-8.
 * 分析底层 Typeface.c 源码，发现每次调用 Typeface.createFormAsset
 * 都会在内存中加载一个新的实例，
 * 关键是分配的这些内存都不会被回收掉，这就造成了内存泄露问题
 * 在 Asset Allocations 那个指标项，内存中共分配了 4 个 Roboto 字体实例，
 * 也就是说每次调用 Typeface.createFromAsset 都会加载一个新的实例到内存中。显
 * 然这个是没有必要的，更何况每个分配的字体资源都不会回收掉。
 * <p>
 * 针对这个问题，网上也有优化方案，那就是将首次解析的字体资源缓存起来，
 * 以后再用到相同的字体资源时，直接取缓存的即可：
 */

public class TypeFaceHelper {
    private static final String TAG = "TypefaceHelper";
    private static final SimpleArrayMap<String, Typeface> TYPEFACE_CACHE = new SimpleArrayMap<>();


    public static Typeface get(Context context, String name) {
        synchronized (TYPEFACE_CACHE) {
            if (!TYPEFACE_CACHE.containsKey(name)) {

                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), name);
                    TYPEFACE_CACHE.put(name, t);
                } catch (Exception e) {
                    Log.e(TAG, "Could not get typeface '" + name
                            + "' because " + e.getMessage());
                    return null;
                }
            }
            return TYPEFACE_CACHE.get(name);
        }


    }


}
