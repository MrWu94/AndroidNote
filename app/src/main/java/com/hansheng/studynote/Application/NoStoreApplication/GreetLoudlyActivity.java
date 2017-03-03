package com.hansheng.studynote.Application.NoStoreApplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.StudyApplication;

/**
 * Created by hansheng on 17-3-2.
 */
public class GreetLoudlyActivity extends AppCompatActivity {

    TextView textview;

    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reading);
        textview = (TextView) findViewById(R.id.message);
    }

    protected void onResume() {
        super.onResume();
        StudyApplication app = (StudyApplication) getApplication();
        textview.setText("HELLO " + app.getName().toUpperCase());
    }
}
