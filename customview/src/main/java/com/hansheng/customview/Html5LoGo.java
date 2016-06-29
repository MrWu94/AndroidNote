package com.hansheng.customview;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.hansheng.customview.R;

/**
 * Created by hansheng on 2016/6/25.
 */
public class Html5LoGo extends View {
    private int ScrHeight;
    private int ScrWidth;

    private Paint mPaint1,mPaint2,mPaint3,mPaint4;

    private int centerX,centerY;

    private int mWidth;


    public Html5LoGo(Context context) {
        super(context);
        initPaint();
    }


    public Html5LoGo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Html5LoGo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

        //屏幕信息
        DisplayMetrics dm = getResources().getDisplayMetrics();
        ScrHeight = dm.heightPixels;
        ScrWidth = dm.widthPixels;
        //设置边缘特殊效果
        BlurMaskFilter PaintBGBlur = new BlurMaskFilter(
                1, BlurMaskFilter.Blur.INNER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getMeasuredWidth() > getHeight())
            mWidth = getMeasuredHeight();
        else
            mWidth = getMeasuredWidth();
    }

    private void initPaint() {

        mPaint1=new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setColor(getResources().getColor(R.color.reg));

        mPaint2=new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setAlpha(3);
        mPaint1.setColor(getResources().getColor(R.color.pag));

        mPaint3=new Paint();
        mPaint3.setAntiAlias(true);
        mPaint3.setStyle(Paint.Style.FILL);
        mPaint3.setColor(getResources().getColor(R.color.five));



    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path1=new Path();
        path1.moveTo(39,250);
        path1.lineTo(17,0);
        path1.lineTo(262,0);
        path1.lineTo(239,250);
        path1.lineTo(139,278);
        path1.close();
        canvas.drawPath(path1,mPaint1);

        Path path4=new Path();
        path4.moveTo(139,257);
        path4.lineTo(220,234);
        path4.lineTo(239,20);
        path4.lineTo(139,20);
        path4.close();
        canvas.drawPath(path4,mPaint2);

        Path path2=new Path();
        path2.moveTo(139,113);
        path2.lineTo(98,113);
        path2.lineTo(96,82);
        path2.lineTo(139,51);
        path2.lineTo(62,51);
        path2.lineTo(70,114);
        path2.lineTo(139,144);
        path2.close();
        canvas.drawPath(path2,mPaint3);

        Path path3=new Path();
        path3.moveTo(139,193);
        path3.lineTo(105,184);
        path3.lineTo(103,159);
        path3.lineTo(76,207);
        path3.lineTo(139,255);
        path3.close();
        canvas.drawPath(path3,mPaint3);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
