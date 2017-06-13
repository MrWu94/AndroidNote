package com.hansheng.studynote.ui.service.music;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-6-13.
 */

public class MusicActivity extends Activity implements Button.OnClickListener {

    Button btnStart;
    Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        btnStart= (Button) findViewById(R.id.btn_start);
        btnStop= (Button) findViewById(R.id.btn_stop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_start){
            //播放背景音乐
            Intent intent = new Intent(this, MusicService.class);
            startService(intent);
            //退出当前Activity
            this.finish();
        }else if(v.getId() == R.id.btn_stop){
            //停止播放音乐
            Intent intent = new Intent(this, MusicService.class);
            stopService(intent);
        }
    }
}