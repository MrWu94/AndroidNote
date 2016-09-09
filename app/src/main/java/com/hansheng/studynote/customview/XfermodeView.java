package com.hansheng.studynote.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-8.
 */

public class XfermodeView extends View {

    private Bitmap mBgBitmap,mFgBitmap;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;

    public XfermodeView(Context context) {
        super(context);
        init();
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawBitmap(mBgBitmap,0,0,null);
//        canvas.drawBitmap(mFgBitmap,0,0,null);

        mBgBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.bitmap);
        mFgBitmap=Bitmap.createBitmap(mBgBitmap.getWidth(),mBgBitmap.getHeight(),Bitmap.Config.ARGB_8888);
        canvas=new Canvas(mFgBitmap);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        canvas.drawRect(0,0,mBgBitmap.getWidth(),mBgBitmap.getHeight(),mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBgBitmap,0,0,mPaint);
    }

    private void init() {

//        mPaint=new Paint();
//        mPaint.setAlpha(0);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeWidth(50);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPath=new Path();
//
//        mBgBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap);
//        mFgBitmap=Bitmap.createBitmap(mBgBitmap.getWidth(),mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        mCanvas=new Canvas(mFgBitmap);
//        mCanvas.drawColor(Color.GRAY);
    }
}
