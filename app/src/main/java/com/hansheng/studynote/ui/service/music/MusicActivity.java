package com.hansheng.studynote.ui.service.music;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        Log.d(TAG, "onCreate: " + getKernelVersion());
        Log.d(TAG, "onCreate: "+getHandSetInfo());
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

    public static String getKernelVersion() {
        String kernelVersion = "";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/proc/version");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return kernelVersion;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8 * 1024);
        String info = "";
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                info += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if (info != "") {
                final String keyword = "version ";
                int index = info.indexOf(keyword);
                line = info.substring(index + keyword.length());
                index = line.indexOf(" ");
                kernelVersion = line.substring(0, index);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return kernelVersion;
    }

    private String getHandSetInfo(){
        String handSetInfo=
                "手机型号:" + Build.MODEL +
                        ",SDK版本:" + Build.VERSION.SDK +
                        ",系统版本:" + Build.VERSION.RELEASE+
                        ",软件版本:"+getAppVersionName(MusicActivity.this);
        return handSetInfo;

    }
    //获取当前版本号
    private  String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.hansheng.studynote", 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
}