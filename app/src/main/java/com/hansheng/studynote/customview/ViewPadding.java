package com.hansheng.studynote.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hansheng on 16-9-13.
 */

public class ViewPadding extends View {

    private Paint paint;
    public ViewPadding(Context context) {
        super(context);
    }

    public ViewPadding(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFF33B5E5);
        paint.setStrokeWidth(4);

        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getWidth() - getPaddingRight();
        int bottom = getHeight() - getPaddingBottom();
        canvas.drawLine(left, top, right, bottom, paint);
    }
}
