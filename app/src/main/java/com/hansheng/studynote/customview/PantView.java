package com.hansheng.studynote.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hansheng on 2016/10/4.
 */

public class PantView extends View {

    private Paint mPaint;
    public PantView(Context context) {
        super(context);
    }

    public PantView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint=new Paint();
        // 设置字体颜色
        mPaint.setColor(Color.rgb(0x06,0xaf,0xcd));

        // 在不同模式下绘制文字
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("左对齐", 0, 200, mPaint);

        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("居中对齐", 0, 400, mPaint);

        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("右对齐", 0, 600, mPaint);

        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.rgb(0x06,0xaf,0xcd));

        // 设置不同的样式

        // 填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("GcsSloop 中文", 0, 200, mPaint);

        // 描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText("GcsSloop 中文", 0, 400, mPaint);

        // 描边加填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("GcsSloop 中文", 0, 600, mPaint);
    }
}
