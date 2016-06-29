package com.hansheng.studynote.handler;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpCookie;

/**
 * Created by hansheng on 2016/6/26.
 */
public class HandlerPostActivity2 extends AppCompatActivity {
    private Button btnDown;
    private ImageView ivImage;
    private static String image_path = "http://ww4.sinaimg.cn/bmiddle/786013a5jw1e7akotp4bcj20c80i3aao.jpg";

    private ProgressDialog dialog;

    private static Handler handler=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler2_layout);
        btnDown = (Button) findViewById(R.id.btnDown);
        ivImage = (ImageView) findViewById(R.id.ivSinaImage);

        dialog=new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("正在下载，请稍后。。。。。");
        dialog.setCancelable(false);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new myThread()).start();
                dialog.show();
            }
        });


    }
    public class myThread implements Runnable{
        @Override
        public void run() {
            HttpClient httpClient=new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(image_path);
            HttpResponse httpResponse=null;

            try {
                httpResponse=httpClient.execute(httpGet);
                if(httpResponse.getStatusLine().getStatusCode()==200){
                    byte[] data= EntityUtils.toByteArray(httpResponse.getEntity());
                    final Bitmap bmp= BitmapFactory.decodeByteArray(data,0,data.length);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ivImage.setImageBitmap(bmp);
                        }
                    });
                    dialog.dismiss();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
