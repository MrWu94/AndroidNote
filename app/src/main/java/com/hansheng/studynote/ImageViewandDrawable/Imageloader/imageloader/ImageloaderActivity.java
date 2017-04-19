package com.hansheng.studynote.ImageViewandDrawable.Imageloader.imageloader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ImageViewandDrawable.Imageloader.ImageLoaderUtil.ImageLoaderFactory;
import com.hansheng.studynote.ImageViewandDrawable.Imageloader.ImageLoaderUtil.ImageLoaderWrapper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageSize;


/**
 * Created by hansheng on 2016/6/29.
 */
public class ImageloaderActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView imageView1;
    String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageloader_layout);
        imageView= (ImageView) findViewById(R.id.img_loader);
        imageView1= (ImageView) findViewById(R.id.img_loader1);
        ImageSize mImageSize = new ImageSize(100, 100);


        ImageLoaderWrapper imageLoaderWrapper=ImageLoaderFactory.getLoader();
        ImageLoaderWrapper.DisplayOption option=new ImageLoaderWrapper.DisplayOption();
        option.loadErrorResId=R.mipmap.img_error;
        option.loadingResId=R.mipmap.img_default;

        imageLoaderWrapper.displayImage(imageView,imageUrl,option);
        imageLoaderWrapper.displayImage(this,imageView1,imageUrl);


        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

//        ImageLoader.getInstance().loadImage(imageUrl,mImageSize ,options, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String s, View view) {
//
//            }
//
//            @Override
//            public void onLoadingFailed(String s, View view, FailReason failReason) {
//
//            }
//
//            @Override
//            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//                imageView.setImageBitmap(bitmap);
//
//            }
//
//            @Override
//            public void onLoadingCancelled(String s, View view) {
//
//            }
//        });



    }
}
