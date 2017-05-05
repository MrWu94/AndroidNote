package com.hansheng.studynote.systemui.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-14.
 */

public class ToolBarActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbat_main);
        toolbar= (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
    }
}
