package com.hansheng.studynote.Activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-23.
 * 通过SpannableString和它的setSpan(Object what, int start, int end, int flags)便可以对textview设置想要的效果了，
 * 这里的what就是效果名，start和end是设置这个样式针对的是textview的字符位置。
 */

public class SpanActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.span_layout);
        textView = (TextView) findViewById(R.id.tv);
        textView1 = (TextView) findViewById(R.id.tv_1);
        textView2 = (TextView) findViewById(R.id.tv_2);
        textView3 = (TextView) findViewById(R.id.tv_3);
        textView4 = (TextView) findViewById(R.id.tv_4);
        textView5 = (TextView) findViewById(R.id.tv_5);
        setBackgroundColorSpan();
        setForegroundColorSpan();
        setStyleSpan();
        setRelativeFontSpan();
        setTypefaceSpan();
        addUrlSpan();
    }

    private void setForegroundColorSpan() {
        SpannableString spanString = new SpannableString("前景色");
        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(spanString);
    }

    private void setBackgroundColorSpan() {
        SpannableString spanString = new SpannableString("背景色");
        BackgroundColorSpan span = new BackgroundColorSpan(Color.YELLOW);
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView1.append(spanString);
    }

    private void setStyleSpan() {
        SpannableString spanString = new SpannableString("粗体斜体");
        StyleSpan span = new StyleSpan(Typeface.BOLD_ITALIC);
        spanString.setSpan(span, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.append(spanString);
    }

    private void setRelativeFontSpan() {
        SpannableString spanString = new SpannableString("字体相对大小");
        spanString.setSpan(new RelativeSizeSpan(2.5f), 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView3.append(spanString);
    }

    private void setTypefaceSpan() {
        SpannableString spanString = new SpannableString("文本字体");
        spanString.setSpan(new TypefaceSpan("monospace"), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView4.append(spanString);
    }

    private void addUrlSpan() {
        SpannableString spanString = new SpannableString("超链接");
        URLSpan span = new URLSpan("http://www.baidu.com");
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView5.append(spanString);
    }

    private void mode6() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView5.setText(spannableString);
    }

    /**
     * 这里就可以看出SpannableStringBuilder的可拼接性，这里同样采用了ForegroundColorSpan为文本设置颜色。
     */

    private void mode2() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV");
        spannableString.append("已经开始暴走了");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(colorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView5.setText(spannableString);
    }

}
