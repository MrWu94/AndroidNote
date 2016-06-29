package com.hansheng.studynote.dragview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by hansheng on 2016/6/22.
 */
public class DragView5 extends View {

    private int lastx;
    private int lastY;
    private Scroller mScroller;

    public DragView5(Context context) {
        super(context);
        initView(context);
}

    public DragView5(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DragView5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x= (int) event.getX();
        int y = (int) event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastx=x-lastx;
                lastY=y-lastY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX=x-lastx;
                int offsetY=x-lastY;
                ((View) getParent()).scrollBy(-offsetX,offsetY);
                break;
            case MotionEvent.ACTION_UP:
                // 手指离开时，执行滑动过程
                View viewGroup = ((View) getParent());
                mScroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY());
                invalidate();
                break;

        }
        return true;

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    private void initView(Context context) {
        setBackgroundColor(Color.BLUE);
        mScroller=new Scroller(context);
    }

}
