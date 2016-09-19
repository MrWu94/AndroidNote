package com.hansheng.studynote.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-19.
 */

public class TextButtonActivity extends AppCompatActivity {
    private final static String tag=TextButtonActivity.class.getSimpleName();
    private TestButton testBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_layout);
        testBtn= (TestButton) findViewById(R.id.testBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i(tag, "testBtn---onClick...");
            }
        });
        testBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i(tag, "testBtn-onTouch-ACTION_DOWN...");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i(tag, "testBtn-onTouch-ACTION_UP...");
                        break;
                    default:break;

                }
                return false;
            }
        });
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(tag, "MainActivity-dispatchTouchEvent-ACTION_DOWN...");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(tag, "MainActivity-dispatchTouchEvent-ACTION_UP...");
                break;
            default:break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(tag, "MainActivity-onTouchEvent-ACTION_DOWN...");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(tag, "MainActivity-onTouchEvent-ACTION_UP...");
                break;
            default:break;
        }
        return super.onTouchEvent(event);
    }
}
