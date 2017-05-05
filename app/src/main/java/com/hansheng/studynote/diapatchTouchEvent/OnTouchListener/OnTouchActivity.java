package com.hansheng.studynote.diapatchTouchEvent.OnTouchListener;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.hansheng.studynote.ui.activity.BaseActivity;
import com.hansheng.studynote.R;

import butterknife.Bind;

/**
 * Created by hansheng on 17-1-11.
 * (01) View中的dispatchTouchEvent()会将事件传递给"自己的onTouch()", "自己的onTouchEvent()"进行处理。
 * 而且onTouch()的优先级比onTouchEvent()的优先级要高。 (02) onTouch()与onTouchEvent()都是View中用户处理触摸事件的API。
 * onTouch是OnTouchListener接口中的函数，OnTouchListener接口需要用户自己实现。onTouchEvent()是View自带的接口，
 * Android系统提供了默认的实现；当然，用户可以重载该API。
 * (03) onTouch()与onTouchEvent()有两个不同之处：(01), onTouch()是View提供给用户，让用户自己处理触摸事件的接口。
 * 而onTouchEvent()是Android系统自己实现的接口。(02)，onTouch()的优先级比onTouchEvent()的优先级更高。
 * dispatchTouchEvent()中分发事件的时候，会先将事件分配给onTouch()进行处理，然后才分配给onTouchEvent()进行处理。
 * 如果onTouch()对触摸事件进行了处理，并且返回true；那么，该触摸事件就不会分配在分配给onTouchEvent()进行处理了。
 * 只有当onTouch()没有处理，或者处理了但返回false时，才会分配给onTouchEvent()进行处理。
 */

public class OnTouchActivity extends BaseActivity implements View.OnTouchListener ,View.OnClickListener{
    private static final String TAG = "OnTouchActivity";
    private int x;
    private int y;
    private int rawx;
    private int rawy;

    @Bind(R.id.btn_shape)
    Button button;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        button.setOnClickListener(this);
        button.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int eventaction = event.getAction();
        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                x = (int) event.getX();
                y = (int) event.getY();
                rawx = (int) event.getRawX();
                rawy = (int) event.getRawY();
                Log.d(TAG, "getX=" + x + "getY=" + y + "\n" + "getRawX=" + rawx
                        + "getRawY=" + rawy + "\n");
                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");
        
    }
}
