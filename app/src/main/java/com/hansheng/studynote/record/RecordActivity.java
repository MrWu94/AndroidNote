package com.hansheng.studynote.record;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by hansheng on 16-12-8.
 * 　MediaRecorder录制视频必不可少的步骤：
 * <p>
 * 只有在Initial初始化状态，才可以对MediaRecorder调用release()释放资源，其他状态必须先stop()或者reset()。
 * 调用new MediaRecorder()构造函数得到MediaRecorder的实例。
 * 调用setOutputFormat()设定媒体文件的输出格式，必须在设定音频与视频的编码格式之前设定。
 * 如果需要记录音频，则调用setAudioSource()设定音频的录入源以及调用setAudioEncoder()设定音频的编码方式。
 * 如果需要记录视频，则调用setVideoSource()设定视频的录入员以及调用setVideoEncoder()设定视频的编码方式。
 * 调用setOutputFile()设定记录的媒体文件保存的路径。
 * 先调用prepare()准备录制，准备完成之后调用start()开始录制。
 * 记录完成后，调用stop()停止录制。
 */

public class RecordActivity extends AppCompatActivity {
    private static final String TAG="RecordActivity";

    private MediaRecorder mediaRecorder;
    private MediaPlayer  mPlayer;
    private boolean isRecording;
    File file;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
         file = new File("/sdcard/mediarecorder.amr");
        if (file.exists()) {
            file.delete();
        }
    }

    public void startRecord(View view) {
        try {
           
            mediaRecorder = new MediaRecorder();
            // 设置音频录入源
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置录制音频的输出格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            // 设置音频的编码格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            // 设置录制音频文件输出文件路径
            mediaRecorder.setOutputFile(file.getAbsolutePath());
            mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {

                @Override
                public void onError(MediaRecorder mr, int what, int extra) {
                    // 发生错误，停止录制
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    isRecording = false;
                    Toast.makeText(RecordActivity.this, "录音发生错误", Toast.LENGTH_SHORT).show();
                }
            });

            // 准备、开始

            mediaRecorder.prepare();

            mediaRecorder.start();

            isRecording = true;

            Toast.makeText(RecordActivity.this, "开始录音", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopRecord(View view) {
        if (isRecording) {
            // 如果正在录音，停止并释放资源
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            Toast.makeText(RecordActivity.this, "录音结束", Toast.LENGTH_SHORT).show();
        }
    }
    public void startPlaying(View view) {
        try {
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(file.getAbsolutePath());//获取绝对路径来播放音频  
            mPlayer.prepare();
            mPlayer.start();
            Toast.makeText(this, "开始播放", Toast.LENGTH_SHORT).show();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub  
                    stopPlaying();
                   
                }
            });
            
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
    }
    private void stopPlaying() {
        if(mPlayer!=null){
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRecording) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}
