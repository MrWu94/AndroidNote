package com.hansheng.studynote.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/9/11.
 */
public class GestureDectorActivity extends AppCompatActivity {
    private static final String TAG="Gesture";
    private VelocityTracker velocityTracker=null;
    GestureDetector gestureDetecto=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetecto=new GestureDetector(this,new GestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetecto.onTouchEvent(event);
        int action=event.getActionIndex();
        switch (action){
            case(MotionEvent.ACTION_DOWN):{
                Log.d(TAG, "Action was Down");
                if (velocityTracker == null) {
                    velocityTracker = VelocityTracker.obtain();
                } else {
                    velocityTracker.clear();
                }
                velocityTracker.addMovement(event);
                return true;
            }
            // 手指在移动
            case (MotionEvent.ACTION_MOVE):
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                // 在获取速度之前总要进行以上两步
                Log.d(TAG, "Action was Move");
                Log.d(TAG, "Pressure and Size: " + event.getPressure() + " " + event.getSize());
                Log.d(TAG, "X,Y Position: " + event.getRawX() + "," + event.getRawY());
                Log.d(TAG, "X,Y velocity: " + velocityTracker.getXVelocity() + "," + velocityTracker.getXVelocity());
                return true;
            // 手指离开屏幕
            case (MotionEvent.ACTION_UP):
                Log.d(TAG, "Action was Up");
                return true;
            // 收到前驱事件后，后续事件被父控件拦截的情况下产生
            case (MotionEvent.ACTION_CANCEL):
                Log.d(TAG, "Action was Cancel");
                return true;
            // 表示用户触碰超出了正常的UI边界
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(TAG, "Action was out of current view");
                return true;
            default:
                return super.onTouchEvent(event);

        }
    }
}
