package com.hansheng.studynote.dragview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hansheng on 2016/6/22.
 */
public class DrawView1 extends View {


    private int lastX;
    private int lastY;


    public DrawView1(Context context) {
        super(context);
        initView();
    }


    public DrawView1(Context context, AttributeSet attrs) {
        super(context, attrs);
       initView();

    }

    public DrawView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x= (int) event.getX();
        int y= (int) event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            lastX=x;
            lastY=y;
            break;
            case MotionEvent.ACTION_UP:
                int offsetX=x-lastX;
                int offsetY=y-lastY;
                layout(getLeft()+offsetX,
                        getTop()+offsetY,
                        getRight()+offsetX,
                        getBottom()+offsetY);
                break;
        }
        return true;
    }

    private void initView() {
        setBackgroundColor(Color.BLUE);
    }

}
