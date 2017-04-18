package com.hansheng.studynote.ImageViewandDrawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
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
        getViewSize(getApplicationContext());

    }

    // 获取屏幕的分辨率
    private void getViewSize(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);




        Log.d("Windmill", "Windmill:"+metrics.heightPixels+"|"+metrics.widthPixels);
    }
}
