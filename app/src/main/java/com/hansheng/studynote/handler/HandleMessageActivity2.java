package com.hansheng.studynote.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/6/26.
 */
public class HandleMessageActivity2 extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4,btn5;
    private static TextView tvMes;
    private static Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==3||msg.what==5){
                tvMes.setText("what="+msg.what+",这是一个空消息");

            }
            else {
                tvMes.setText("what="+msg.what+","+msg.obj.toString());
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handlermessage_layout);


        tvMes = (TextView) findViewById(R.id.tvMes);
        btn1 = (Button) findViewById(R.id.btnMessage1);
        btn2 = (Button) findViewById(R.id.btnMessage2);
        btn3 = (Button) findViewById(R.id.btnMessage3);
        btn4 = (Button) findViewById(R.id.btnMessage4);
        btn5 = (Button) findViewById(R.id.btnMessage5);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg=Message.obtain();
                        msg.what=1;
                        msg.obj="使用Message .obtain+Handler.sendMessage()发送消息";
                        handler.sendMessage(msg);

                    }
                }).start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                      Message msg=Message.obtain();
                        msg.what=2;
                        msg.obj="使用Message.sendToTarget发送消息";
                        msg.sendToTarget();
                    }
                }).start();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(3);
                    }
                }).start();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg=Message.obtain();
                        msg.what=4;
                        msg.obj="使用Message.Obtain+Hander.sendMessage()发送延迟消息";
                        handler.sendMessageDelayed(msg,3000);
                    }
                }).start();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessageDelayed(5,3000);
                    }
                }).start();
            }
        });


    }
}
