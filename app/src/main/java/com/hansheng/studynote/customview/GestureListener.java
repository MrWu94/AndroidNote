package com.hansheng.studynote.customview;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by hansheng on 2016/9/11.
 * 很多时候我们并不需要获取用户手势操作的原始数据，而是仅仅想判断手势操作所属的类型，那么我们可以在处理触摸操作的类中实现 OnTouchListener 接口。
 * 接口 OnTouchListener 需要实现的方法：
 * onDown(MotionEvent event) 手指落下
 * onFling(MotionEvent event) 手指划过（快速）
 * onLongPress(MotionEvent event) 长按
 * onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) 手指滑动（较慢）
 * onShowPress(MotionEvent event) 激发了 Down 事件但手指还未移动
 * onSingleTapUp(MotionEvent event) 单击，抬起手指
 * onDoubleTap(MotionEvent event) 双击，抬起手指
 * onDoubleTapEvent(MotionEvent event) 双击事件
 * onSingleTapConfirmed(MotionEvent event) 确认为单击事件
 */
public class GestureListener extends GestureDetector.SimpleOnGestureListener {
    public static final String TAG = "GestureListener";

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG, "Single Tap Up");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(TAG, "Long Press");
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i(TAG, "Scroll");
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // 注意这个方法只有在 up 动作后才会被调用
        Log.i(TAG, "Fling");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // 用户执行来 down 的动作,但是还没有移动
        Log.i(TAG, "Show Press");
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i(TAG, "Down");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i(TAG, "Double tap");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        // 双击(double-tap)实际上是由几个事件组合而成,过程中中 down/up/move 事件同时也会激调用该方法
        Log.i(TAG, "Event within double tap");
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        // 仅当 detector 确认这是一次单击,而不是双击的一部分时才会调用该方法
        Log.i(TAG, "Single tap confirmed");
        return false;
    }
}
