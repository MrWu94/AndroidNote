package com.hansheng.studynote.Activity.ViewStub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/10/2.
 */

public class ViewStubActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstub);
        button= (Button) findViewById(R.id.clickMe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewStub myViewStub = (ViewStub)findViewById(R.id.myViewStub);
                if (myViewStub != null) {
                    myViewStub.inflate();
                    //或者是下面的形式加载
                    //myViewStub.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
