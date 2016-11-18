package com.hansheng.studynote.custonview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/7/19.
 */
public class BarActivity extends AppCompatActivity {
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
//
//        final ValueSelector valueSelector = (ValueSelector) findViewById(R.id.valueSelector);
//        valueSelector.setMinValue(0);
//        valueSelector.setMaxValue(100);
//
//        final ValueBar valueBar = (ValueBar) findViewById(R.id.valueBar);
//        valueBar.setMaxValue(100);
//        valueBar.setAnimated(true);
//        valueBar.setAnimationDuration(4000l);
//
//        updateButton = (Button) findViewById(R.id.updateButton);
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int value = valueSelector.getValue();
//                valueBar.setValue(value);
//
//                //code to use Object Animation instead of the built-in ValueBar animation
//                //if you use this, be sure the call valueBar.setAnimated(false);
//                /*
//                ObjectAnimator anim = ObjectAnimator.ofInt(valueBar, "value", valueBar.getValue(), value);
//                anim.setDuration(1000);
//                anim.start();
//                */
//            }
//        });
    }
}
