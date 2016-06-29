package com.hansheng.studynote.handler;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/6/26.
 */
public class HandlerPostActivity extends AppCompatActivity {

    private Button btnMes1,btnMes2;
    private TextView tvMessage;

    private static Handler handler=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_layout);

        btnMes1=(Button)findViewById(R.id.btnMes1);
        btnMes2=(Button)findViewById(R.id.btnMes2);
        tvMessage=(TextView)findViewById(R.id.tvMessage);

        btnMes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tvMessage.setText("使用Handler.post在工作线程中发送一段执行到消息队列中，在主线程中执行。");
                            }
                        });
                    }
                }).start();
            }
        });

        btnMes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                               tvMessage.setText("使用Handler.postDelayed在工作线程中发送一段执行到消息队列中，在主线程中延迟3S执行。");
                            }
                        },3000);
                    }
                }).start();
            }
        });
    }
}
