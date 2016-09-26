package com.hansheng.studynote.FlipLibrary.demo;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by hansheng on 2016/9/25.
 */

public class NumberTextView extends TextView {

    private int number;

    public NumberTextView(Context context, int number) {
        super(context);
        setNumber(number);
        setTextColor(Color.BLACK);
        setBackgroundColor(Color.WHITE);
        setGravity(Gravity.CENTER);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        setText(String.valueOf(number));
    }

    @Override
    public String toString() {
        return "NumberTextView: " + number;
    }
}
