package com.hansheng.studynote.Gesture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-14.
 * VISIBLE：设置控件可见
 * <p>
 * INVISIBLE：设置控件不可见
 * <p>
 * GONE：设置控件隐藏
 * 而INVISIBLE和GONE的主要区别是：当控件visibility属性为INVISIBLE时，
 * 界面保留了view控件所占有的空间；而控件属性为GONE时，界面则不保留view控件所占有的空间。
 */

public class VisiibleActivity extends AppCompatActivity {
    /**
     * TextView2
     */
    private TextView mainTV2 = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visibile_layout);
        //初始化数据
        initData();

    }

    /**
     * 初始化控件的方法
     */
    private void initData() {
        mainTV2 = (TextView) findViewById(R.id.mainTV2);
    }

    /**
     * MainActivity中响应按钮点击事件的方法
     *
     * @param v
     */
    public void mianOnClickListener(View v) {
        switch (v.getId()) {
            case R.id.mainBtn1: {    //按钮1的响应事件
                //设置TextView2可见
                mainTV2.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.mainBtn2: {    //按钮2的响应事件
                //设置TextView2不可见
                mainTV2.setVisibility(View.INVISIBLE);
                break;
            }
            case R.id.mainBtn3: {    //按钮3的响应事件
                //设置TextView2隐藏
                mainTV2.setVisibility(View.GONE);
                break;
            }
            default:
                break;
        }
    }
}
