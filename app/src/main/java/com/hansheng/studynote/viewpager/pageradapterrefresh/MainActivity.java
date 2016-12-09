package com.hansheng.studynote.viewpager.pageradapterrefresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_refresh);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simpledemo:
                overlay(SimpleDemoActivity.class);
                break;
            case R.id.btn_pageradapter:
                overlay(PagerAdapterActivity.class);
                break;
            case R.id.btn_fpageradapter1:
                overlay(FPagerAdapter1Activity.class);
                break;
            case R.id.btn_fpageradapter2:
                overlay(FPagerAdapter2Activity.class);
                break;
            case R.id.btn_fspageradapter:
                overlay(FSPagerAdapterActivity.class);
                break;
        }
    }

    private void overlay(Class<? extends Activity> clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }

}
