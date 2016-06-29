package com.hansheng.studynote.scroller;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by hansheng on 2016/6/29.
 */
public class ScrollerLayout extends ViewGroup {


    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;

    /**
     * 手机按下时的屏幕坐标
     */
    private float mXDown;

    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;

    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;


    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
        ViewConfiguration configuration=ViewConfiguration.get(context);
        mTouchSlop= ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mXMove=event.getRawX();
                int scrolledX= (int) (mXLastMove-mXMove);
                if(getScrollX()+scrolledX<leftBorder){
                    scrollTo(leftBorder,0);
                    return  true;
                }
                else if(getScrollX()+getWidth()+scrolledX>rightBorder){
                    scrollTo(rightBorder-getWidth(),0);
                    return  true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex=(getScrollX()+getWidth()/2)/getWidth();
                int dx=targetIndex*getWidth()-getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(),0,dx,0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
      if (mScroller.computeScrollOffset()){
          scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
          invalidate();
      }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mXDown=ev.getRawX();
                mXLastMove=mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove=ev.getRawX();
                float diff=Math.abs(mXMove-mXDown);
                mXLastMove=mXDown;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if(diff>mTouchSlop){
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int childCount=getChildCount();
            for(int i=0;i<childCount;i++){
                View childView=getChildAt(i);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i*childView.getMeasuredWidth(),0,(i+1)*childView.getMeasuredWidth(),childView.getMeasuredHeight());
            }
            // 初始化左右边界值
            leftBorder=getChildAt(0).getLeft();
            rightBorder=getChildAt(getChildCount()-1).getRight();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }
}
