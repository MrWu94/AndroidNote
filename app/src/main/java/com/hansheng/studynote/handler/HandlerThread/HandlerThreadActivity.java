package com.hansheng.studynote.handler.HandlerThread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by wfq on 2016/11/28.
 * HandlerThread适合在只需要在一个工作线程(非UI线程)+任务的等待队列的形式,优点是不会有堵塞，
 * 减少了对性能的消耗，缺点是不能同时进行多任务的处理,需要等待进行处理。处理效率较低
 * Message 意为消息，发送到Handler进行处理的对象，携带描述信息和任意数据。
 * MessageQueue 意为消息队列，Message的集合。
 * Looper 有着一个很难听的中文名字，消息泵，用来从MessageQueue中抽取Message，发送给Handler进行处理。
 * Handler 处理Looper抽取出来的Message。
 */

public class HandlerThreadActivity extends AppCompatActivity {
    private HandlerThread handlerThread;
    private ImageView imageView, imageView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handlerthread_main);
        init();
    }

    private void init() {
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView1 = (ImageView) findViewById(R.id.imageView1);

        handlerThread = new HandlerThread("MainActivity");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper());

        //点击download开始进行下载
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new MyRunable(1));
                handler.post(new MyRunable(2));
            }
        });
    }

    class MyRunable implements Runnable {
        int pos;

        public MyRunable(int pos) {
            this.pos = pos;
        }


        @Override
        public void run() {
            //模拟耗时
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (pos == 1) {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setBackgroundResource(R.mipmap.ic_launcher);
                    }
                });
            } else {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView1.setBackgroundResource(R.mipmap.ic_launcher);
                    }
                });
            }


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();//停止Looper的循环
    }
}
