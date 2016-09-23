package com.hansheng.studynote.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hansheng on 16-9-23.
 * 1. 在一个类初始化时，即在构造函数当中我们是得不到View的实际大小的。感兴趣的朋友可以试一下，
 * getWidth()和getMeasuredWidth()得到的结果都是0.但是我们可以从onDraw()方法裡面得到控件的大小。
 * 2. 这两个方法所得到的结果的单位是像素即pixel.
 * 对两个方法做介绍：
 * getWidth(): 得到的是view在父Layout中布局好后的宽度值，如果没有父布局，那麼默认的父布局是整个屏幕。也许不好理解。
 */

public class Test extends Activity {
    private LinearLayout mBackgroundLayout;
    private TextViewTest mTextViewTest;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBackgroundLayout = new MyLayout(this);
        mBackgroundLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));

        mTextViewTest = new TextViewTest(this);

        mBackgroundLayout.addView(mTextViewTest);
        setContentView(mBackgroundLayout);
    }

    public class MyLayout extends LinearLayout {

        public MyLayout(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            // TODO Auto-generated method stub
            super.onLayout(changed, l, t, r, b);
            Log.i("Tag", "--------------");
            View mView = getChildAt(0);
            mView.measure(0, 0);
        }

    }

    public class TextViewTest extends TextView {
        public TextViewTest(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            setText("test test ");
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
            // measure(0, 0);
            Log.i("Tag", "width: " + getWidth() + ",height: " + getHeight());
            Log.i("Tag", "MeasuredWidth: " + getMeasuredWidth()
                    + ",MeasuredHeight: " + getMeasuredHeight());
        }

    }
}