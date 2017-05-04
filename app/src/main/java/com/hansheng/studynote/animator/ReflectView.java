package com.hansheng.studynote.animator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/6/23.
 */
public class ReflectView extends View {


    private Bitmap mSrcBitmap, mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;


    public ReflectView(Context context) {
        super(context);
        init();
    }

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zhangjinxuan);
        Matrix matrix = new Matrix();
        matrix.setScale(1f, -1f);
        //实现图像的垂直翻转
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);
        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(), 0, mSrcBitmap.getHeight() / 4,
                0xdd000000, 0x10000000, Shader.TileMode.CLAMP
        ));
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        mPaint.setXfermode(mXfermode);
        canvas.drawRect(0,mSrcBitmap.getHeight(),mRefBitmap.getWidth(),mSrcBitmap.getHeight()*2,mPaint);
        mPaint.setXfermode(null);
    }
}
