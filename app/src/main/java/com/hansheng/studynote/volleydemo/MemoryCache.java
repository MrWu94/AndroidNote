package com.hansheng.studynote.volleydemo;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by hansheng on 2016/6/28.
 */
public class MemoryCache implements ImageLoader.ImageCache {

    private LruCache<String,Bitmap> mCache;

    public MemoryCache() {
        int maxSize=10*1024*1024;
        mCache=new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        Log.i("cache","从内存中拿数据");
        return mCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        mCache.put(s,bitmap);

    }
}
