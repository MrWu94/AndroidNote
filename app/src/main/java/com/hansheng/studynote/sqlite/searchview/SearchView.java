package com.hansheng.studynote.sqlite.searchview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hansheng.studynote.R;

/**
 * Android自定义SearchView
 * Created by yuandl on 2016-11-17.
 */

public class SearchView extends LinearLayout implements TextWatcher, View.OnClickListener {
    /**
     * 输入框
     */
    private EditText et_search;
    /**
     * 输入框后面的那个清除按钮
     */
    private Button bt_clear;


    private InputChangeListener inputChangeListener;

    /**
     * 设置输入框文字变化监听
     *
     * @param inputChangeListener
     */
    public void setInputChangeListener(InputChangeListener inputChangeListener) {
        this.inputChangeListener = inputChangeListener;
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**加载布局文件*/
        LayoutInflater.from(context).inflate(R.layout.pub_searchview, this, true);
        /***找出控件*/
        et_search = (EditText) findViewById(R.id.et_search);
        bt_clear = (Button) findViewById(R.id.bt_clear);
        bt_clear.setVisibility(GONE);
        et_search.addTextChangedListener(this);
        bt_clear.setOnClickListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    /****
     * 对用户输入文字的监听
     *
     * @param editable
     */
    @Override
    public void afterTextChanged(Editable editable) {
        /**获取输入文字**/
        String input = et_search.getText().toString().trim();
        if (input.isEmpty()) {
            bt_clear.setVisibility(GONE);
        } else {
            bt_clear.setVisibility(VISIBLE);
        }
        if (inputChangeListener != null) {
            inputChangeListener.textChange(input);
        }
    }

    @Override
    public void onClick(View view) {
        et_search.setText("");
    }

    /**
     * 获取用户输入的文本
     *
     * @return
     */
    public String getInputText() {
        return et_search.getText().toString().trim();
    }

    /**
     * 输入框文字变化监听接口
     */
    public interface InputChangeListener {
        /**
         * 输入的文字
         *
         * @param text
         */
        void textChange(String text);
    }
}
