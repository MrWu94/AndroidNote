package com.hansheng.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hansheng on 2016/6/26.
 */
public class SimpleLayout extends ViewGroup {


    public SimpleLayout(Context context) {
        super(context);
    }

    public SimpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if(getChildCount()>0){
            View childView=getChildAt(0);
            //子视图在SimpleLayout中左上右下四个点的坐标
            //调用childView.getMeasuredWidth()和childView.getMeasuredHeight()方法得到的值就是在onMeasure()方法中测量出的宽和高。
           childView.layout(0,0,childView.getMeasuredWidth(),childView.getMeasuredHeight());

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(getChildCount()>0){
            View childView=getChildAt(0);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);

        }
    }
}
