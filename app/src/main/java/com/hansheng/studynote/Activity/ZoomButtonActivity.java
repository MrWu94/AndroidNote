package com.hansheng.studynote.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ZoomButton;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-11-9.
 * ZoomButton，称为放大按钮。实际上它继承于ImageButton，并在ImageButton基础上增加了“按下ZoomButton时，
 * 会不断上报点击事件”。至于上报的时间间隔，可以通过setZoomSpeed()去设置。
 */

public class ZoomButtonActivity extends AppCompatActivity implements View.OnClickListener{
    // ZoomButton
    private static int pauseSize= 12;
    private TextView mTvShow;
    private ZoomButton mZoomPause;

    // ImageButton
    private static int pauseCom= 12;
    private TextView mTvCom;
    private ImageButton mBtnCom;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_test);
        mTvShow = (TextView)findViewById(R.id.tv_show);
        mZoomPause = (ZoomButton)findViewById(R.id.zoom_pause);
        mZoomPause.setOnClickListener(this);

        mTvCom = (TextView)findViewById(R.id.tv_com);
        mBtnCom = (ImageButton)findViewById(R.id.btn_com);
        mBtnCom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.zoom_pause:{
                // 按住ZoomButton不方，会不断的执行下面的操作：将文本放大
                pauseSize += 2;
                mTvShow.setTextSize(pauseSize);
                break;
            }
            case R.id.btn_com:{
                // 按住ImageButton，只有松开时才会执行下面操作：将文本放大
                pauseCom += 2;
                mTvCom.setTextSize(pauseCom);
                break;
            }
            default:
                break;
        }
    }
}
