package com.hansheng.studynote.handler;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpCookie;

/**
 * Created by hansheng on 2016/6/26.
 */
public class HandlerMessageActivity extends AppCompatActivity {
    private Button btnDown;


    private ImageView ivImage;
     private static String image_path = "http://ww4.sinaimg.cn/bmiddle/786013a5jw1e7akotp4bcj20c80i3aao.jpg";
    private ProgressDialog dialog;

    private static int IS_FINISH=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler2_layout);
        btnDown = (Button) findViewById(R.id.btnDown);
        ivImage = (ImageView) findViewById(R.id.ivSinaImage);

        dialog = new ProgressDialog(this);
        dialog.setTitle("提示信息");
        dialog.setMessage("正在下载，请稍后...");
        dialog.setCancelable(false);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new MyThread()).start();
                dialog.show();
            }
        });


    }
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==IS_FINISH){
                byte[] data= (byte[]) msg.obj;
                Bitmap bmp= BitmapFactory.decodeByteArray(data,0,data.length);
                ivImage.setImageBitmap(bmp);
                dialog.dismiss();

            }
        }
    };

    public class MyThread implements Runnable{

        @Override
        public void run() {
            HttpClient httpClient=new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(image_path);
            HttpResponse httpResponse=null;
            try {
                httpResponse=httpClient.execute(httpGet);
                if(httpResponse.getStatusLine().getStatusCode()==200){
                    byte[] data= EntityUtils.toByteArray(httpResponse.getEntity());

                    Message msg=Message.obtain();
                    msg.obj=data;
                    msg.what=IS_FINISH;
                    handler.sendMessage(msg);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
