package com.hansheng.studynote.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by hansheng on 2016/7/5.
 */
public class HorizontalScrollViewEx  extends ViewGroup{

    private static final String TAG="HorizontalScrollViewEx";

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildrenIndex;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;


    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;


    public HorizontalScrollViewEx(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       mVelocityTracker.addMovement(event);
        int x= (int) event.getX();
        int y= (int) event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            if(!mScroller.isFinished()){
                mScroller.abortAnimation();//优化滑动体验
            }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX=x-mLastX;
                int deltaY=y-mLastY;
                scrollBy(-deltaX,0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX=getScrollX();
                int scrollToChildIndex=scrollX/mChildWidth;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity=mVelocityTracker.getXVelocity();
                if(Math.abs(xVelocity)>=50){
                    mChildrenIndex=xVelocity>0?mChildrenIndex-1:mChildrenIndex+1;
                }else {
                    mChildrenIndex=(scrollX+mChildWidth/2)/mChildWidth;
                }
                mChildrenIndex = Math.max(0, Math.min(mChildrenIndex, mChildrenSize - 1));
                int dx = mChildrenIndex * mChildWidth - scrollX;
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();
                break;
            default:
                break;

        }
        mLastX=x;
        mLastY=y;
        return true;
    }

    private void smoothScrollBy(int dx, int i) {
        mScroller.startScroll(getScrollX(),0,dx,0,500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        boolean intercepted=false;
        int x= (int) event.getX();
        int y= (int) event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercepted=false;
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                    intercepted=true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX=x-mLastXIntercept;
                int delataY=y-mLastYIntercept;
                if(Math.abs(deltaX)>Math.abs(delataY)){
                    intercepted=true;
                }
                else {
                    intercepted=false;
                }
            case MotionEvent.ACTION_UP:
                intercepted=false;
                break;
            default:
                break;

        }

        Log.d(TAG,"intercepted="+intercepted);
        mLastX=x;
        mLastY=y;
        mLastXIntercept=x;
        mLastYIntercept=y;
        return intercepted;
    }

    @Override
    protected void onDetachedFromWindow() {
      mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }

    private void init() {
        mScroller=new Scroller(getContext());
        mVelocityTracker=VelocityTracker.obtain();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft=0;
        final int childCount=getChildCount();
        mChildrenSize=childCount;
        for(int i=0;i<childCount;i++){
            final View childView=getChildAt(i);
            if(childView.getVisibility()!=View.GONE){
                final int childWidth=childView.getMeasuredWidth();
                mChildWidth=childWidth;
                childView.layout(childLeft,0,childLeft+childWidth,childView.getMeasuredHeight());
                childLeft+=childWidth;
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = 0;
        int measuredHeight = 0;
        final int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize, childView.getMeasuredHeight());
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(measuredWidth, heightSpaceSize);
        } else {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        }
    }
}
