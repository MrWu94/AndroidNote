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
 */
public class ImageActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        imageView= (ImageView) findViewById(R.id.image_view);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.bitmap);
        imageView.setImageBitmap(ImageUtils.compressImage(bitmap));
}
}
