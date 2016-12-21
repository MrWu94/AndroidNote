package com.hansheng.studynote.Activity.LaunchMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hansheng.studynote.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hansheng on 16-12-21.
 */
public class TestCActivity extends AppCompatActivity {

    private static final String TAG = "DEBUG-WCL: " + TestCActivity.class.getSimpleName();

    @Bind(R.id.main_tv_text)
    TextView mTvText;
    @Bind(R.id.main_b_jump)
    Button mBJump;
    @Bind(R.id.main_b_jump_2) Button mBJump2;
    @Bind(R.id.main_b_jump_3) Button mBJump3;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        mTvText.setText(String.valueOf("Activity C"));
        mBJump.setText(String.valueOf("创建[Activity D]"));
        mBJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestCActivity.this, TestDActivity.class));
            }
        });

        mBJump2.setVisibility(View.VISIBLE);
        mBJump2.setText(String.valueOf("创建[Activity B]"));
        mBJump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestCActivity.this, TestBActivity.class));
            }
        });

        mBJump3.setVisibility(View.VISIBLE);
        mBJump3.setText(String.valueOf("创建[Activity A]"));
        mBJump3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestCActivity.this, TestAActivity.class));
            }
        });
    }

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.e(TAG, "onNewIntent");
    }

    @Override protected void onStart() {
        super.onStart();

        Log.e(TAG, "onStart");
    }

    @Override protected void onResume() {
        super.onResume();

        Log.e(TAG, "onResume");
    }

    @Override protected void onPause() {
        super.onPause();

        Log.e(TAG, "onPause");
    }

    @Override protected void onStop() {
        super.onStop();

        Log.e(TAG, "onStop");
    }

    @Override protected void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "onDestroy");
    }
}

