package com.hansheng.studynote.ImageViewandDrawable.Imageloader.ImageLoaderUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hansheng.studynote.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.io.File;

/**
 * Created by hansheng on 17-1-9.
 */
public class UniversalAndroidImageLoader implements ImageLoaderWrapper {

    private final static String HTTP = "http";
    private final static String HTTPS = "https";
    private static final String TAG="UniversalAndroid";


    UniversalAndroidImageLoader() {
    }

    @Override
    public void displayImage(ImageView imageView, File imageFile, DisplayOption option) {

        int imageLoadingResId = R.mipmap.img_default;
        int imageErrorResId = R.mipmap.img_error;
        if (option != null) {
            imageLoadingResId = option.loadingResId;
            imageErrorResId = option.loadErrorResId;
        }
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(imageLoadingResId)
                .showImageForEmptyUri(imageErrorResId)
                .showImageOnFail(imageErrorResId)
                .cacheInMemory(true) //加载本地图片不需要再做SD卡缓存，只做内存缓存即可
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        String uri;
        if (imageFile == null) {
            uri = "";
        } else {
            uri = ImageDownloader.Scheme.FILE.wrap(imageFile.getAbsolutePath());
        }
        ImageLoader.getInstance().displayImage(uri, imageView, options);

    }

    @Override
    public void displayImage(ImageView imageView, String imageUrl, DisplayOption option) {
        int imageLoadingResId = R.mipmap.img_default;
        int imageErrorResId = R.mipmap.img_error;
        if (option != null) {
            imageLoadingResId = option.loadingResId;
            imageErrorResId = option.loadErrorResId;
        }
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(imageLoadingResId)
                .showImageForEmptyUri(imageErrorResId)
                .showImageOnFail(imageErrorResId)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        if (imageUrl.startsWith(HTTPS)) {
            String uri = ImageDownloader.Scheme.HTTPS.wrap(imageUrl);
            ImageLoader.getInstance().displayImage(uri, imageView, options);
        } else if (imageUrl.startsWith(HTTP)) {
            String uri = ImageDownloader.Scheme.HTTP.wrap(imageUrl);
            Log.d(TAG, "displayImage: "+uri);
            ImageLoader.getInstance().displayImage(imageUrl, imageView, options);
        }
    }

    @Override
    public void displayImage(Context context, ImageView imageView, String imageUri) {
        Glide.with(context).load(imageUri).crossFade().into(imageView);

    }

    @Override
    public void displayImage(Context context, ImageView imageView, @DrawableRes int imgRes) {
        Glide.with(context).load(imgRes).centerCrop().crossFade().into(imageView);

    }


    public static void init(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());

    }
}
