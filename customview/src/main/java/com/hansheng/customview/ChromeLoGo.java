package com.hansheng.customview;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by hansheng on 2016/6/25.
 */
public class ChromeLoGo extends View {
    private Paint mPaintRed,mPaintYellow,mPaintGreen,mPaintBulue,mPaintWhite,mPaintLine;

    private float mWidth=0f;
    private float mPadding=0f;

    ArgbEvaluator evaluator;

    private int endColor= Color.rgb(0,0,0);
    private int startYellowcolor=Color.argb(100,253,197,53);
    private int startGreenColor=Color.argb(100,27,147,76);
    private int startRedColor=Color.argb(100,211,57,53);

    private int lineColor;
    RotateAnimation mProgressRotateAnim;



    public ChromeLoGo(Context context) {
        this(context,null);
    }

    public ChromeLoGo(Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }

    public ChromeLoGo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(getMeasuredHeight()>getHeight()){
            mWidth=getMeasuredHeight();
        }else{
            mWidth=getMeasuredWidth();
        }
        mPadding=dip2x(1);
    }

    private int dip2x(float i)
    {
        final float scale=getContext().getResources().getDisplayMetrics().density;
        return (int) (i*scale+0.5f);
    }

    private void initPaint() {
        evaluator=new ArgbEvaluator();
        mPaintRed=new Paint();
        mPaintRed.setAntiAlias(true);
        mPaintRed.setStyle(Paint.Style.FILL);
        mPaintRed.setColor(Color.rgb(211,57,53));

        mPaintYellow=new Paint();
        mPaintYellow.setAntiAlias(true);
        mPaintYellow.setStyle(Paint.Style.FILL);
        mPaintYellow.setColor(Color.rgb(253,197,53));
        mPaintGreen = new Paint();
        mPaintGreen.setAntiAlias(true);
        mPaintGreen.setStyle(Paint.Style.FILL);
        mPaintGreen.setColor(Color.rgb(27, 147, 76));


        mPaintBulue = new Paint();
        mPaintBulue.setAntiAlias(true);
        mPaintBulue.setStyle(Paint.Style.FILL);
        mPaintBulue.setColor(Color.rgb(61, 117, 242));

        mPaintWhite = new Paint();
        mPaintWhite.setAntiAlias(true);
        mPaintWhite.setStyle(Paint.Style.FILL);
        mPaintWhite.setColor(Color.WHITE);


        mPaintLine = new Paint();
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStyle(Paint.Style.FILL);
        mPaintLine.setColor(Color.argb(30, 0, 0, 0));

        mProgressRotateAnim=new RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF
        ,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mProgressRotateAnim.setRepeatCount(-1);
        mProgressRotateAnim.setInterpolator(new LinearInterpolator());
        mProgressRotateAnim.setFillAfter(true);

    }
}
