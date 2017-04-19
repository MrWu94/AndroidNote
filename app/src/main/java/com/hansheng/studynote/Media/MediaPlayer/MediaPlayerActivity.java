package com.hansheng.studynote.Media.MediaPlayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hansheng.studynote.R;

import java.io.IOException;

/**
 * Created by hansheng on 17-4-19.
 */

public class MediaPlayerActivity extends AppCompatActivity {
    private Button bplay, bpause, bstop;
    private MediaPlayer mp = new MediaPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);

        bplay = (Button) findViewById(R.id.play);
        bpause = (Button) findViewById(R.id.pause);
        bstop = (Button) findViewById(R.id.stop);
        bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mp.setDataSource("http://nyan.90g.org/d/0/88/2cfddf877e1603d6ccfaf5b1688cd6ed.mp3");
                    mp.prepare();
                    mp.start();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }
        });

        bpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.pause();
                }
            }
        });

        bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.stop();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mp != null)
            mp.release();
        super.onDestroy();
    }
}
