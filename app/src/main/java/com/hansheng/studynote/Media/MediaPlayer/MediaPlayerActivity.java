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
 * 如何设置要播放的文件：
 * MediaPlayer要播放的文件主要包括3个来源：
 * a. 用户在应用中事先自带的resource资源
 * 例如：MediaPlayer.create(this, R.raw.test);
 * b. 存储在SD卡或其他文件路径下的媒体文件
 * 例如：mp.setDataSource("/sdcard/test.mp3");
 * c. 网络上的媒体文件
 * 例如：mp.setDataSource("http://www.citynorth.cn/music/confucius.mp3");
 * <p>
 * 对播放器的主要控制方法：
 * Android通过控制播放器的状态的方式来控制媒体文件的播放，其中：
 * prepare()和prepareAsync()  提供了同步和异步两种方式设置播放器进入prepare状态，需要注意的是，
 * 如果MediaPlayer实例是由create方法创建的，那么第一次启动播放前不需要再调用prepare（）了，因为create方法里已经调用过了。
 * start()是真正启动文件播放的方法，
 * pause()和stop()比较简单，起到暂停和停止播放的作用，
 * <p>
 * seekTo()是定位方法，可以让播放器从指定的位置开始播放，需要注意的是该方法是个异步方法，
 * 也就是说该方法返回时并不意味着定位完成，尤其是播放的网络文件，真正定位完成时会触发OnSeekComplete.onSeekComplete()，
 * 如果需要是可以调用setOnSeekCompleteListener(OnSeekCompleteListener)设置监听器来处理的。
 * release()可以释放播放器占用的资源，一旦确定不再使用播放器时应当尽早调用它释放资源。
 * reset()可以使播放器从Error状态中恢复过来，重新会到Idle状态
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
