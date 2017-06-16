package com.hansheng.studynote.ui.service.music;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.androidutils.VersionUtil;

/**
 * Created by hansheng on 17-6-13.
 */

public class MusicActivity extends Activity implements Button.OnClickListener {
    public static final String TAG = "MusicActivity";

    Button btnStart;
    Button btnStop;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_stop);
        image = (ImageView) findViewById(R.id.btn_img);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        Log.d(TAG, "onCreate: " + VersionUtil.getKernelVersion());
        Log.d(TAG, "onCreate: " + VersionUtil.getHandSetInfo(this));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            //播放背景音乐
            Intent intent = new Intent(this, MusicService.class);
            startService(intent);
            //退出当前Activity
            this.finish();
        } else if (v.getId() == R.id.btn_stop) {
            //停止播放音乐
            Intent intent = new Intent(this, MusicService.class);
            stopService(intent);
        }
    }


}