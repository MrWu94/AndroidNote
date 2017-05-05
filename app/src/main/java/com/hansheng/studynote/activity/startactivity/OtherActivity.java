package com.hansheng.studynote.activity.startactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-5.
 */
public class  OtherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_result);
    }
    public void SetResult(View view){

        //数据是使用Intent返回
        Intent intent = new Intent();
        //把返回数据存入Intent
        intent.putExtra("result", "My name is hansheng");
        //设置返回数据
        OtherActivity.this.setResult(RESULT_OK, intent);
        //关闭Activity
        OtherActivity.this.finish();

    }
}
