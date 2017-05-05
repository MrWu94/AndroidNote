package com.hansheng.studynote.activity.textutils;

import android.text.TextUtils;
import android.util.Log;

import com.hansheng.studynote.activity.BaseActivity;
import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-1-20.
 */

public class TextUtilsActivity extends BaseActivity {
    private static final String TAG="TextUtilsActivity";
    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Log.d(TAG, "---------------------------------");
        //字符串拼接  
        Log.d(TAG, TextUtils.concat("Hello", " ", "world!").toString());
        //判断是否为空字符串  
        Log.d(TAG, TextUtils.isEmpty("Hello") + "");
        //判断是否只有数字  
        Log.d(TAG, TextUtils.isDigitsOnly("Hello") + "");
        //判断字符串是否相等  
        Log.d(TAG, TextUtils.equals("Hello", "Hello") + "");
        //获取字符串的倒序  
        Log.d(TAG, TextUtils.getReverse("Hello", 0, "Hello".length()).toString());
        //获取字符串的长度  
        Log.d(TAG, TextUtils.getTrimmedLength("Hello world!") + "");
        Log.d(TAG, TextUtils.getTrimmedLength("  Hello world!  ") + "");
        //获取html格式的字符串  
        Log.d(TAG, TextUtils.htmlEncode("<html>\n" +
                "<body>\n" +
                "这是一个非常简单的HTML。\n" +
                "</body>\n" +
                "</html>"));
        //获取字符串中第一次出现子字符串的字符位置  
        Log.d(TAG, TextUtils.indexOf("Hello world!", "Hello") + "");
        //截取字符串  
        Log.d(TAG, TextUtils.substring("Hello world!", 0, 5));
        //通过表达式截取字符串  
        Log.d(TAG, TextUtils.split("  Hello world!  ", " ")[0]);

    }
}
