package com.hansheng.studynote.AndroidAnnoation;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.hansheng.studynote.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hansheng on 17-2-13.
 */

@EActivity(R.layout.annotation_main)
public class Annotations extends AppCompatActivity {
    @ViewById(R.id.myInput)
    EditText myInput;

    @ViewById(R.id.myTextView)
    TextView textView;

    @Click
    void myButton() {
        String name = myInput.getText().toString();
        textView.setText("Hello "+name);
    }
}
