package com.hansheng.studynote.scroller;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by hansheng on 2016/6/22.
 */
public class SwipeBackFrameLayout extends FrameLayout {

    public static final String TAG=SwipeBackFrameLayout.class.getSimpleName();
    private ViewDragHelper mDragHelper;

    private int mLastdx;
    private View mDividerView;
    private View mContentView;

    private int mDividerWidth;


    public SwipeBackFrameLayout(Context context) {
        this(context,null);
    }

    public SwipeBackFrameLayout(Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }

    public SwipeBackFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mDividerWidth=mDividerView.getWidth();
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return mDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDividerView=getChildAt(0);
        mDividerView.setAlpha(0f);
        mContentView=getChildAt(1);
    }

    private void init() {
        mDragHelper=ViewDragHelper.create(this,1f,new ViewDragHelper.Callback(){
            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                if (mDragHelper.getViewDragState()==ViewDragHelper.STATE_IDLE&&mCallback != null&&mDividerWidth==mContentView.getLeft()&&mLastdx>0) {
                    mCallback.onShouldFinish();
                }
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                float alphe=(float)(left*1.0/mDividerWidth);
                mDividerView.setAlpha(alphe);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if(mLastdx>0) {
                    if (mDividerWidth!=releasedChild.getLeft()) {
                        mDragHelper.settleCapturedViewAt(mDividerWidth, releasedChild.getTop());
                        invalidate();
                    } else {
                        if (mCallback != null) {
                            mCallback.onShouldFinish();
                        }
                    }
                }
                else{
                    if(mDividerWidth!=0){
                        mDragHelper.settleCapturedViewAt(0,releasedChild.getTop());
                        invalidate();
                    }
                }

            }
            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
                mDragHelper.captureChildView(mContentView, pointerId);
            }

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return false;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                mLastdx=dx;
                int newLeft=Math.min(mDividerWidth,Math.max(left,0));
                return newLeft;
            }
        });
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    public void setCallback(Callback mCallback){
        this.mCallback=mCallback;
    }
    private Callback mCallback;
    public interface Callback{
        void onShouldFinish();
    }
}
