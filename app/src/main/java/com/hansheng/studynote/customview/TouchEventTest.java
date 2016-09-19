package com.hansheng.studynote.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hansheng on 16-9-18.
 */

public class TouchEventTest extends View {
    private OnClickListener mListener;
    private onViewClick mViewClick;
    private int startRawX;
    private int startRawY;

    public TouchEventTest(Context context) {
        this(context, null);
    }

    public TouchEventTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchEventTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Logger.e("view内部设置OnClickListener", "");
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startRawX = rawX;
                startRawY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                if (x + getLeft() < getRight() && y + getTop() < getBottom()) {
                    mListener.onClick(this);
                    mViewClick.onClick(rawX - startRawX, rawY - startRawY);
                }
                break;
        }
        return true;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mListener = l;
    }

    public void setOnViewClick(onViewClick click) {
        this.mViewClick = click;
    }


    public interface onViewClick {
        /**
         * @param scrollX 从按下到抬起,X轴方向移动的距离
         * @param scrollY 从按下到抬起,Y轴方向移动的距离
         */
        void onClick(float scrollX, float scrollY);
    }
}
