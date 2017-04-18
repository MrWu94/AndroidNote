package com.hansheng.studynote.ImageViewandDrawable.bitmapcache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by hansheng on 2016/6/26.
 */
public class MemoryCacheUtils {

    private LruCache<String,Bitmap> mLruCache;
    // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
    // LruCache通过构造函数传入缓存值，以KB为单位。
    long maxMemory=Runtime.getRuntime().maxMemory();
    public MemoryCacheUtils(){
        mLruCache=new LruCache<String,Bitmap>((int) (maxMemory/8)){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。

                int byteCount=value.getRowBytes()*value.getWidth();
                return byteCount;

            }
        };

    }

    public Bitmap getFromMemory(String url){
        return mLruCache.get(url);
    }
    public void setToMemory(String url,Bitmap bitmap){
        mLruCache.put(url,bitmap);
    }
}
