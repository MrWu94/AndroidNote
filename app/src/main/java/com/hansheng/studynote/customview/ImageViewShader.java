package com.hansheng.studynote.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-8.
 */

public class ImageViewShader extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;

    public ImageViewShader(Context context) {
        super(context);

    }

    public ImageViewShader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewShader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap);
        mBitmapShader=new BitmapShader(mBitmap,Shader.TileMode.CLAMP,Shader.TileMode.REPEAT);

        mPaint=new Paint();
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(500,250,200,mPaint);

    }
}
