package com.hansheng.studynote.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by hansheng on 16-9-19.
 * onSizeChanged的启动时间在onDraw之前
 */

public class TestView extends View {

    public TestView(Context context) {
        super(context);
        Log.d("mDebug", "TestView context");
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("mDebug", "TestView context, attrs="+attrs.getAttributeValue(0));
    }

    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.d("mDebug", "TestView context,attrs,defStyle attrs="+attrs.getAttributeValue(0));
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("mDebug", "onDraw");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("mDebug", "onFinishInflate");
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("mDebug", "onSizeChanged,w="+w+",h="+h+",oldw="+oldw+",oldh="+oldh);
    }

}