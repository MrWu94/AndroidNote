package com.hansheng.studynote.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by wfq on 2016/11/9.
 */

public class ActivityOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_options);
    }
    public void start(View view){
        Intent intent=new Intent(ActivityOptionsActivity.this,ClockActivity.class);
        ActivityOptions activityOptions=ActivityOptions.makeCustomAnimation(ActivityOptionsActivity.this,R.anim.fade_in,R.anim.fade_out);
//        startActivity(intent,activityOptions.toBundle());

        ActivityOptionsCompat activityOptionsCompat=ActivityOptionsCompat.makeCustomAnimation(ActivityOptionsActivity.this,R.anim.fade_out,R.anim.fade_in);
        ActivityCompat.startActivity(ActivityOptionsActivity.this,intent,activityOptionsCompat.toBundle());



    }
}
