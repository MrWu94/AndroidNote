package com.hansheng.studynote.media.music.musicplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.widget.MediaController;

/**
 * Created by hansheng on 2016/7/20.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MusicController extends MediaController {

    public MusicController(Context c){
        super(c);
    }

    public void hide(){}

}