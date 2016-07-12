package com.hansheng.studynote.inject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/7/12.
 */
@ContentView(value = R.layout.inject_layuot)
public class InjectActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(R.id.mBtn1)
    private Button mBtn1;
    @ViewInject(R.id.mBtn2)
    private Button mBtn2;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewInjectUtils.inject(this);

    }

    @OnClick(R.id.mBtn1)
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.mBtn1:
                Toast.makeText(InjectActivity.this, "Why do you click me ?",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.mBtn2:
                Toast.makeText(InjectActivity.this, "I am sleeping !!!",
                        Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
