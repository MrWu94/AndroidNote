package com.hansheng.studynote.eventbus.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/7/13.
 *
 */
public class EventBusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_layout);
    }
}
