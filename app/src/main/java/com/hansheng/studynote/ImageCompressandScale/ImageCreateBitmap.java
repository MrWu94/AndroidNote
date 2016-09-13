package com.hansheng.studynote.ImageCompressandScale;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-13.
 */

public class ImageCreateBitmap extends AppCompatActivity {

    private ImageView imgView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        imgView= (ImageView) findViewById(R.id.image_view);
        Bitmap bitmap = Bitmap.createBitmap(10,10, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        imgView.draw(canvas);

    }
}
