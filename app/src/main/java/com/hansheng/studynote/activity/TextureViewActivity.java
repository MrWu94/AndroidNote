package com.hansheng.studynote.activity;

import android.content.res.AssetManager;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by hansheng on 16-11-10.
 */

public class TextureViewActivity  extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    //	private TextureView textureView;
    private MediaPlayer mMediaPlayer;
    private Surface surface;

    private ImageView videoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texture_main);
        TextureView textureView=(TextureView) findViewById(R.id.textureview);
        textureView.setSurfaceTextureListener(this);//设置监听函数  重写4个方法

//		textureView=new TextureViewTest(this);
//		setContentView(textureView);
        videoImage=(ImageView) findViewById(R.id.video_image);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width,int height) {
        System.out.println("onSurfaceTextureAvailable onSurfaceTextureAvailable");
        surface=new Surface(surfaceTexture);
        new PlayerVideo().start();//开启一个线程去播放视频
    }
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        System.out.println("onSurfaceTextureSizeChanged onSurfaceTextureSizeChanged");
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        System.out.println("onSurfaceTextureDestroyed onSurfaceTextureDestroyed");
        surfaceTexture=null;
        surface=null;
        mMediaPlayer.stop();
        mMediaPlayer.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
//		System.out.println("onSurfaceTextureUpdated onSurfaceTextureUpdated");
    }

    private class PlayerVideo extends Thread{
        @Override
        public void run(){
            try {
                File file=new File(Environment.getExternalStorageDirectory()+"/ansen.mp4");
                if(!file.exists()){//文件不存在
                    copyFile();
                }
                mMediaPlayer= new MediaPlayer();
                mMediaPlayer.setDataSource(file.getAbsolutePath());
                mMediaPlayer.setSurface(surface);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp){
                        videoImage.setVisibility(View.GONE);
                        mMediaPlayer.start();
                    }
                });
                mMediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public interface PlayerController{
        public void play();
    }

    /**
     * 如果sdcard没有文件就复制过去
     */
    private void copyFile() {
        AssetManager assetManager = this.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open("ansen.mp4");
            String newFileName = Environment.getExternalStorageDirectory()+"/ansen.mp4";
            out = new FileOutputStream(newFileName);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
    }
}
