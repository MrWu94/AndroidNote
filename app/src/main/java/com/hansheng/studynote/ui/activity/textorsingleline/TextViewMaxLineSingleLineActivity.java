package com.hansheng.studynote.ui.activity.textorsingleline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-24.
 * 深入理解View(一)：从setContentView谈起
 * http://www.jianshu.com/p/3b4dc52fbae4
 */

public class TextViewMaxLineSingleLineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maxline_main);
        Log.d("TAG", getWindow().getClass().getName());
    }
}
