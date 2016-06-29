package com.hansheng.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by hansheng on 2016/6/20.
 */
public class MagicCircle extends View {

    private Path mPath;
    private Paint mFillCirclePaint;

    private int width;
    private int height;
    private int centerX;

    private int centerY;

    private float maxLength;
    private float mInterpolatedTime;

    private float stretchDistance;
    private float moveDistance;
    private float cDistance;

    private float radius;
    private float c;
    private float blackMagic=0.551915024494f;

    private VPoint p2,p4;
    private HPoint p1,p3;

    public MagicCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        canvas.translate(radius,radius);
        if(mInterpolatedTime>=0&&mInterpolatedTime<=0.2){
            model1(mInterpolatedTime);
        }

        float offset=maxLength*(mInterpolatedTime-0.2f);
        offset=offset>0?offset:0;
        p1.adjustAllX(offset);
        p2.adjustAllX(offset);

        p3.adjustAllX(offset);
        p4.adjustAllX(offset);

        mPath.moveTo(p1.x,p2.y);
        mPath.cubicTo(p1.right.x,p1.right.y,p2.bottom.x,p2.bottom.y,p2.x,p2.y);
        mPath.cubicTo(p2.top.x,p2.top.y,p3.right.x,p3.right.y,p3.x,p3.y);
        mPath.cubicTo(p3.left.x,p3.left.y,p4.top.x,p4.top.y,p4.x,p4.y);
        mPath.cubicTo(p4.bottom.x,p4.bottom.y,p1.left.x,p1.left.y,p1.x,p1.y);

        canvas.drawPath(mPath,mFillCirclePaint);
    }

    private void model0(){
        p1.setY(radius);
        p3.setY(-radius);
        p3.x=p1.x=0;
        p3.x=p1.x=0;
        p3.left.x=p1.left.x=-c;
        p3.right.x=p1.right.x=c;

        p2.setX(radius);
        p4.setX(-radius);
        p2.y=p4.y=0;
        p2.top.y=p4.top.y=-c;
        p2.bottom.y=p4.bottom.y=c;

    }

    private void model1(float mInterpolatedTime) {
        model0();
        p2.setX(radius+stretchDistance*mInterpolatedTime*5);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width=getWidth();
        height=getHeight();

        centerX=width/2;
        centerY=height/2;

        radius=50;
        c=radius*blackMagic;

        stretchDistance=radius;

        moveDistance=radius*(3/5f);
        cDistance=c*0.45f;
        maxLength=width-radius-radius;

    }

    public MagicCircle(Context context, AttributeSet attrs) {
      this(context,attrs,0);
    }

    public MagicCircle(Context context) {
        this(context,null,0);
    }

    private void init() {
        mFillCirclePaint=new Paint();
        mFillCirclePaint.setColor(0xFFfe626d);
        mFillCirclePaint.setStyle(Paint.Style.FILL);
        mFillCirclePaint.setStrokeWidth(1);
        mFillCirclePaint.setAntiAlias(true);

        mPath=new Path();
        p2=new VPoint();
        p4=new VPoint();

        p1=new HPoint();
        p3=new HPoint();
    }

    class VPoint{
        private float x;
        private float y;

        public PointF top=new PointF();
        public PointF bottom=new PointF();

        public void setX(float x){
            this.x=x;
            top.x=x;
            bottom.x=x;
        }
        public void adjustY(float offset){
            top.y-=offset;
            bottom.y+=offset;
        }
        public void adjustAllX(float offset){
            this.x+=offset;
            top.x=offset;
            bottom.x+=offset;
        }
    }

    class HPoint{
        private float x;
        private float y;
        public PointF left=new PointF();
        public PointF right=new PointF();

        public void setY(float y){
            this.y=y;
            left.y=y;
            right.y=y;
        }
        public void adjustAllX (float offset){
            this.x+=offset;
            left.x+=offset;
            right.x+=offset;
        }
    }

    private class MoveAnimation extends Animation{

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mInterpolatedTime=interpolatedTime;
            invalidate();
        }

    }
    public void startAnimation(){
        mPath.reset();
        mInterpolatedTime=0;
        MoveAnimation move=new MoveAnimation();
        move.setDuration(1000);
        move.setInterpolator(new AccelerateInterpolator());
        startAnimation(move);
    }
}
