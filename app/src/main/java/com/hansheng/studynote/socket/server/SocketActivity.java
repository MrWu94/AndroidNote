package com.hansheng.studynote.socket.server;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by hansheng on 2016/7/25.
 */
public class SocketActivity extends AppCompatActivity {
    Socket socket = null;
    String buffer = "";
    TextView txt1;
    Button send;
    EditText ed1;
    String geted1;

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x11){
                Bundle bundle=msg.getData();
                txt1.append("server:"+bundle.getString("msg")+"\n");
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_main);
        txt1 = (TextView) findViewById(R.id.txt1);
        send = (Button) findViewById(R.id.send);
        ed1 = (EditText) findViewById(R.id.ed1);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geted1 = ed1.getText().toString();
                txt1.append("client:"+geted1+"\n");
                //启动线程 向服务器发送和接收信息
                new MyThread(geted1).start();
            }
        });
    }
    class MyThread extends Thread{

        String txt;
        public MyThread(String txt){
            this.txt=txt;
        }
        @Override
        public void run() {
           Message msg=new Message();
            msg.what=0x11;
            Bundle bundle=new Bundle();
            bundle.clear();

            socket=new Socket();
            try {
                socket.connect(new InetSocketAddress("127.0.0.1",30000),5000);
                OutputStream out=socket.getOutputStream();
                BufferedReader buf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line=null;
                buffer="";
                while((line = buf.readLine()) != null){
                    buffer=line+buffer;
                }
                //向服务器发送信息
                out.write("android 客户端".getBytes("gbk"));
                out.flush();
                bundle.putString("msg", buffer.toString());
                msg.setData(bundle);
                //发送消息 修改UI线程中的组件
                handler.sendMessage(msg);
                //关闭各种输入输出流
                buf.close();
                out.close();
                socket.close();

            } catch (IOException e) {
                //连接超时 在UI界面显示消息
                bundle.putString("msg", "服务器连接失败！请检查网络是否打开");
                msg.setData(bundle);
                //发送消息 修改UI线程中的组件
                handler.sendMessage(msg);
            }
        }
    }
}
