package com.hansheng.studynote.Activity;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-20.
 */

public class SecondActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text);
        editText= (EditText) findViewById(R.id.edit_text);
        // 获取该Activity对应的Intent的Component属性
        ComponentName comp = getIntent().getComponent();
        // 显示ComponentName对象的包名、类名
        editText.setText("组件包名：" + comp.getPackageName() + "\n组件类名为："
                + comp.getClassName());
    }
}
