package com.hansheng.studynote.animator.higeanim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/6/23.
 */
public class RoundRectShader extends View {

    private BitmapShader mBitmapShader;
    private Bitmap mBitmap;
    private Paint mPaint;

    public RoundRectShader(Context context) {
        super(context);
    }

    public RoundRectShader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundRectShader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.test);
        mBitmapShader=new BitmapShader(mBitmap,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        mPaint=new Paint();
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(500,250,200,mPaint);
    }
}
