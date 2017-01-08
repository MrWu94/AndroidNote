package com.hansheng.studynote.ImageCompressandScale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/9/11.
 * Bitmap类的构造方法都是私有的，所以开发者不能直接new出一个Bitmap对象，只能通过BitmapFactory类的
 * 各种静态方法来实例化一个Bitmap。仔细查看BitmapFactory的源代码可以看到，生成Bitmap对象最终都是通过
 * JNI调用方式实现的。所以，加载Bitmap到内存里以后，是包含两部分内存区域的。简单的说，一部分是Java部分的，
 * 一部分是C部分的。这个Bitmap对象是由Java部分分配的，不用的时候系统就会自动回收了，但是那个对应的C可用的
 * 内存区域，虚拟机是不能直接回收的，这个只能调用底层的功能释放。所以需要调用recycle()方法来释放C部分的内存。
 * 从Bitmap类的源代码也可以看到，recycle()方法里也的确是调用了JNI方法了的。
 */
public class ImageBitmapActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView image;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        imageView = (ImageView) findViewById(R.id.image_view);
        image = (ImageView) findViewById(R.id.imagecustom);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.bitmap));
        imageView.setBackgroundColor(getResources().getColor(R.color.app_color));
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
//        imageView.setForeground(getResources().getDrawable(R.drawable.bitmap));
//        imageView.setAlpha(100);
        BitmapFactory.decodeResource(getResources(), R.drawable.zhangjinxuan);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bitmap);
//        imageView.setImageBitmap(ImageUtils.compressImage(bitmap));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
            System.gc();
            //调用System.gc()并不能保证立即开始进行回收过程
        }
    }
}
