package com.hansheng.studynote.jni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-4-10.
 */

public class MainActivity extends AppCompatActivity {
    static {
//        System.loadLibrary("hello");
        System.loadLibrary("hello_jni"); // 注意没有前缀lib和后缀.so
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(helloJni());
    }

    //声明一个本地方法，用native关键字修饰
//    public native String getStringFromNative();

    public static native String helloJni();

    public static native int addCalc(int a, int b);

}
