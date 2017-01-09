package com.hansheng.studynote.loader.ImageLoaderUtil;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by hansheng on 17-1-9.
 */

public interface  ImageLoaderWrapper {

    public void displayImage(ImageView imageView, File imageFile, DisplayOption options);

    public void displayImage(ImageView imageView,String imageUri,DisplayOption option);

    public void displayImage(Context context, ImageView imageView, String imageUri);

    public void displayImage(Context context, ImageView imageView,@DrawableRes int imgRes);

    public static class DisplayOption{
        public int loadingResId;

        public int loadErrorResId;
    }

}
