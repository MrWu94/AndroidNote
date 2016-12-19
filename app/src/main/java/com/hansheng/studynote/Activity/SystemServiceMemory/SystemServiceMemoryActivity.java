package com.hansheng.studynote.Activity.SystemServiceMemory;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-19.
 */

public class SystemServiceMemoryActivity extends AppCompatActivity {
    private static PowerManager powerManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_main);
        powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
    }
}
