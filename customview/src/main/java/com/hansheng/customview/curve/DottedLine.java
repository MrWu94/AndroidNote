package com.hansheng.customview.curve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hansheng on 2016/6/21.
 */
public class DottedLine extends View {

    private Paint paintLine;

    public DottedLine(Context context) {
        super(context);
    }

    public DottedLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DottedLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paintLine=new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(1);
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);

        Path pathline=new Path();
        pathline.moveTo(0,getHeight()/2);
        pathline.lineTo(getWidth(),getHeight()/2);
        PathEffect effects=new DashPathEffect(new float[]{3,3,3,3,},1);
        paintLine.setPathEffect(effects);
        canvas.drawPath(pathline,paintLine);
    }
}
