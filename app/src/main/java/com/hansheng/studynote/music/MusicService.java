package com.hansheng.studynote.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hansheng on 2016/7/20.
 *
 */
public class MusicService extends Service {

    private static final String TAG = "MusicService";
    private MediaPlayer mediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "MusicSevice onCreate()"
                , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onCreate()");
//        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.yanyuan);
        //设置可以重复播放
        mediaPlayer.setLooping(true);

//        //set uri
//        Uri trackUri = ContentUris.withAppendedId(
//                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                currSong);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Toast.makeText(this, "MusicSevice onStart()"
                , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onStart()");
        mediaPlayer.start();


    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MusicSevice onDestroy()"
                , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onDestroy()");
        mediaPlayer.stop();
        super.onDestroy();
    }

    //其他对象通过bindService 方法通知该Service时该方法被调用
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "MusicSevice onBind()"
                , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onBind()");
        mediaPlayer.start();
        return null;
    }

    //其它对象通过unbindService方法通知该Service时该方法被调用
    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "MusicSevice onUnbind()"
                , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onUnbind()");

        mediaPlayer.stop();

        return super.onUnbind(intent);
    }
}
