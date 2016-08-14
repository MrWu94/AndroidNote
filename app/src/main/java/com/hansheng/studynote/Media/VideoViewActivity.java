package com.hansheng.studynote.Media;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.hansheng.studynote.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hansheng on 2016/8/14.
 */
public class VideoViewActivity extends AppCompatActivity {

    private static final String TAG = "VideoViewDemo";

    private VideoView mVideoView;
    private EditText mPath;
    private ImageButton mPlay;
    private ImageButton mPause;
    private ImageButton mReset;
    private ImageButton mStop;
    private String current;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_main);
        mVideoView = (VideoView) findViewById(R.id.surface_view);

        mPath = (EditText) findViewById(R.id.path);
        mPath.setText("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4");

        mPlay = (ImageButton) findViewById(R.id.play);
        mPause = (ImageButton) findViewById(R.id.pause);
        mReset = (ImageButton) findViewById(R.id.reset);
        mStop = (ImageButton) findViewById(R.id.stop);

        mPlay.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                playVideo();
            }
        });
        mPause.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (mVideoView != null) {
                    mVideoView.pause();
                }
            }
        });
        mReset.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (mVideoView != null) {
                    mVideoView.seekTo(0);
                }
            }
        });
        mStop.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (mVideoView != null) {
                    current = null;
                    mVideoView.stopPlayback();
                }
            }
        });
        runOnUiThread(new Runnable(){
            public void run() {
                playVideo();
            }

        });
    }

    private void playVideo() {
        try {
            final String path = mPath.getText().toString();
            Log.v(TAG, "path: " + path);
            if (path == null || path.length() == 0) {
                Toast.makeText(VideoViewActivity.this, "File URL/path is empty",
                        Toast.LENGTH_LONG).show();

            } else {
                // If the path has not changed, just start the media player
                if (path.equals(current) && mVideoView != null) {
                    mVideoView.start();
                    mVideoView.requestFocus();
                    return;
                }
                current = path;
                mVideoView.setVideoPath(getDataSource(path));
                mVideoView.start();
                mVideoView.requestFocus();

            }
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            if (mVideoView != null) {
                mVideoView.stopPlayback();
            }
        }
    }

    private String getDataSource(String path) throws IOException {
        if (!URLUtil.isNetworkUrl(path)) {
            return path;
        } else {
            URL url = new URL(path);
            URLConnection cn = url.openConnection();
            cn.connect();
            InputStream stream = cn.getInputStream();
            if (stream == null)
                throw new RuntimeException("stream is null");
            File temp = File.createTempFile("mediaplayertmp", "dat");
            temp.deleteOnExit();
            String tempPath = temp.getAbsolutePath();
            FileOutputStream out = new FileOutputStream(temp);
            byte buf[] = new byte[128];
            do {
                int numread = stream.read(buf);
                if (numread <= 0)
                    break;
                out.write(buf, 0, numread);
            } while (true);
            try {
                stream.close();
            } catch (IOException ex) {
                Log.e(TAG, "error: " + ex.getMessage(), ex);
            }
            return tempPath;
        }
    }
}
