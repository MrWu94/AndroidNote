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
 *    Last modified 16-9-28 上午2:20
 *
 */

package com.hansheng.studynote.diapatchTouchEvent.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class RootView extends RelativeLayout {
    private static final String TAG = "DiapatchTouch";

    public RootView(Context context) {
        super(context);
    }

    public RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // Log.i(TAG, Static.dispatchTouchEvent + "呼叫技术部,老板要做淘宝,下周上线.");
            //Log.i(TAG, Static.dispatchTouchEvent + "技术部,老板说按钮不好看,要加一道光.");
            Log.i(TAG, "[经理]"+Static.dispatchTouchEvent + "技术部,你们的app快做完了么?");
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.i(TAG, Static.onInterceptTouchEvent + "(老板可能疯了,但又不是我做.)");
            Log.i(TAG, "[经理]"+Static.onInterceptTouchEvent );
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.i(TAG, Static.onTouchEvent+"报告老板, 技术部说做不了");
            Log.i(TAG, "[经理]"+Static.onTouchEvent );
        }
        return super.onTouchEvent(event);
    }
}
