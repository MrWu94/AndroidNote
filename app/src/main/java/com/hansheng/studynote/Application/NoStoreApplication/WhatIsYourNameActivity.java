package com.hansheng.studynote.Application.NoStoreApplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hansheng.studynote.R;
import com.hansheng.studynote.StudyApplication;

/**
 * Created by hansheng on 17-3-2.
 */

public class WhatIsYourNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Just assume that in the real app we would really ask it!
        StudyApplication app = (StudyApplication) getApplication();
        app.setName("Developer Phil");
        startActivity(new Intent(this, GreetLoudlyActivity.class));

    }

}