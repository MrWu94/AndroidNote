package com.hansheng.studynote.Activity.startActivityForActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-5.
 */

public class StartActivityForActivity extends AppCompatActivity {
    private static final String TAG = "StartActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据
        Log.d(TAG, result);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);
    }

    public void Result(View view) {
        //得到新打开Activity关闭后返回的数据
        //第二个参数为请求码，可以根据业务需求自己编号
        startActivityForResult(new Intent(StartActivityForActivity.this, OtherActivity.class), 1);
    }

}
