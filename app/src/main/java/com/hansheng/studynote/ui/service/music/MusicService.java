package com.hansheng.studynote.ui.service.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.io.IOException;

/**
 * Created by hansheng on 17-6-13.
 */

public class MusicService extends Service {
    public static final String TAG="MusicService";

    private MediaPlayer mediaPlayer = null;

    private boolean isReady = false;

    @Override
    public void onCreate() {
        //onCreate在Service的生命周期中只会调用一次
        Log.d(TAG, "onCreate: ");
        super.onCreate();

        //初始化媒体播放器
        mediaPlayer = MediaPlayer.create(this, R.raw.beep);
        if(mediaPlayer == null){
            return;
        }

        mediaPlayer.stop();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.release();
                stopSelf();
                return false;
            }
        });

        try{
            mediaPlayer.prepare();
            isReady = true;
        } catch (IOException e) {
            e.printStackTrace();
            isReady = false;
        }

        if(isReady){
            //将背景音乐设置为循环播放
            mediaPlayer.setLooping(true);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //每次调用Context的startService都会触发onStartCommand回调方法
        //所以onStartCommand在Service的生命周期中可能会被调用多次
        Log.d(TAG, "onStartCommand: ");
        if(isReady && !mediaPlayer.isPlaying()){
            //播放背景音乐
            mediaPlayer.start();
            Toast.makeText(this, "开始播放背景音乐", Toast.LENGTH_LONG).show();
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //该Service中不支持bindService方法，所以此处直接返回null
        return null;
    }

    @Override
    public void onDestroy() {
        //当调用Context的stopService或Service内部执行stopSelf方法时就会触发onDestroy回调方法
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                //停止播放音乐
                mediaPlayer.stop();
            }
            //释放媒体播放器资源
            mediaPlayer.release();
            Toast.makeText(this, "停止播放背景音乐", Toast.LENGTH_LONG).show();
        }
    }
}
