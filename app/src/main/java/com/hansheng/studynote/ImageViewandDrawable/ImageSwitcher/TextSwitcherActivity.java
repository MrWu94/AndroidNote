package com.hansheng.studynote.ImageViewandDrawable.ImageSwitcher;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-19.
 */

public class TextSwitcherActivity extends AppCompatActivity {
    private TextSwitcher textSwitcher;
    private String[] strs=new String[]
            {
                    "Android将军",
                    "ios将军",
                    "Cocos2d-X将军",
                    "将军"

            };
    private int curStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texswitcher_layout);
        textSwitcher=(TextSwitcher)findViewById(R.id.textSwitcher);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                TextView tv=new TextView(TextSwitcherActivity.this);
                tv.setTextSize(40);
                tv.setTextColor(Color.MAGENTA);
                return tv;
            }
        });
        //调用next方法显示下一个字符串
        next(null);
    }
    //事件处理函数，控制显示下一个字符串
    public void next(View source)
    {
        textSwitcher.setText(strs[curStr++%strs.length]);
    }


}
