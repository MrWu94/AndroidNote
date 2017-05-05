package com.hansheng.studynote.imageordrawable.CalculateBitmapMemory;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-4-12.
 */

public class CalculateBitmapMemoryActivity extends AppCompatActivity{
    public static final String TAG="CalculateBitmapMemory";
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        imageView= (ImageView) findViewById(R.id.image_view);
        Drawable drawable=imageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitDrawable = (BitmapDrawable) drawable;
            Bitmap bit = bitDrawable.getBitmap();
            int rowBytes = bit.getRowBytes();
            int height = bit.getHeight();
            long memSize = rowBytes * height;
            Log.d(TAG, "memSize =" + memSize + "B =" + formatFileSize(memSize));
            Log.d(TAG, "onCreate: memory="+formatFileSize(bit.getByteCount()));
        }
    }

    public static String formatFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }
}
