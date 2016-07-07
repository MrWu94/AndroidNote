package com.hansheng.studynote.eventbus.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by hansheng on 2016/7/6.
 */
public class EventActivity extends AppCompatActivity{
    private Button button;
    private TextView textview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);
        button= (Button) findViewById(R.id.button02);
        textview= (TextView) findViewById(R.id.textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("hello event"));
                EventBus.getDefault().postSticky(new MessageEvent("hello hansheng"));
            }
        });
    }

    @Subscribe
    public void onmessageEvent(MessageEvent event){
        Toast.makeText(EventActivity.this,event.message, Toast.LENGTH_SHORT).show();
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event){
        textview.setText(event.message);
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
}
