/*
 * Copyright 2016 GcsSloop
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 *    Last modified 16-9-28 上午2:23
 *
 */

package com.hansheng.studynote.diapatchTouchEvent.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class ViewGroupA extends RelativeLayout {
    private static final String TAG = Static.TAG3;

    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.i(TAG, Static.dispatchTouchEvent + "老板要做淘宝,下周上线?");
            //Log.i(TAG, Static.dispatchTouchEvent + "给按钮加上一道光.");
            Log.i(TAG, Static.dispatchTouchEvent + "项目进度?");
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.i(TAG, Static.onInterceptTouchEvent + "(看着不太靠谱,先问问小王怎么看)");
            Log.i(TAG, Static.onInterceptTouchEvent);
        }

        // return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.i(TAG, Static.onTouchEvent + "小王说做不了");
            //Log.i(TAG, Static.onTouchEvent);
            Log.i(TAG, Static.onTouchEvent+"正在测试,明天就测试完了");
        }
        return true;
    }
}
