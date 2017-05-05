package com.hansheng.studynote.activity.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hansheng on 16-12-21.
 */
public class TestDActivity extends AppCompatActivity {

    private static final String TAG = "DEBUG-WCL: " + TestDActivity.class.getSimpleName();

    private static final int REQUEST_CODE = 0;
    private static final String EXTRA_DATA = "extra_data";

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

        mTvText.setText(String.valueOf("Activity D"));
        mBJump.setText(String.valueOf("创建自己"));
        mBJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(TestDActivity.this, TestDActivity.class), REQUEST_CODE);
            }
        });

        mBJump2.setVisibility(View.VISIBLE);
        mBJump2.setText(String.valueOf("创建[Activity B]"));
        mBJump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(TestDActivity.this, TestBActivity.class), REQUEST_CODE);
            }
        });

        mBJump3.setVisibility(View.VISIBLE);
        mBJump3.setText(String.valueOf("关闭自己"));
        mBJump3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_DATA, "我是D!");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        Log.e(TAG, "onCreate");
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String text = data.getStringExtra(EXTRA_DATA);
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        } else if (requestCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
        }
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