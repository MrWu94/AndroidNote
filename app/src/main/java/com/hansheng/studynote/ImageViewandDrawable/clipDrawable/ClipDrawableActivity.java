package com.hansheng.studynote.ImageViewandDrawable.clipDrawable;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hansheng on 2016/10/6.
 */

public class ClipDrawableActivity extends AppCompatActivity {
    private ImageView view=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clip);
        view= (ImageView) findViewById(R.id.view);
        //获取图片所显示的ClipDrawable对象
        final ClipDrawable drawable=(ClipDrawable) view.getDrawable();
        final Handler handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //如果消息是本程序发送的
                if(msg.what==0x123)
                {
                    //修改ClipDrawable的level值
                    drawable.setLevel(drawable.getLevel()+200);
                }
            }
        };
        final Timer timer=new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Message msg=new Message();
                msg.what=0x123;
                //发送消息，通知应用修改ClipDrawable的level的值
                handler.sendMessage(msg);
                //取消定时器
                if(drawable.getLevel()>=10000)
                {
                    timer.cancel();
                }
            }
        }, 0,300);
    }
}
