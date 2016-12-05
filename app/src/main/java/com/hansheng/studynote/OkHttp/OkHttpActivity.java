package com.hansheng.studynote.OkHttp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hansheng.studynote.R;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hansheng on 2016/7/4.
 */
public class OkHttpActivity extends AppCompatActivity {
    Button bstart, bpost;
    ImageView imageView;
    TextView tv;
    private final String url = "http://serviceapi.skholingua.com/images/skholingua_image.png";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_layout);
        imageView = (ImageView) findViewById(R.id.Iview);
        tv = (TextView) findViewById(R.id.tvBytes);
        bstart = (Button) findViewById(R.id.bStart);

        bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpHandler handler = new OkHttpHandler();
                byte[] image = new byte[0];

                try {
                    image = handler.execute(url).get();
                    if (image != null && image.length > 0) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                        imageView.setImageBitmap(bitmap);
                        tv.setText("Total btytes download: " + image.length);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    tv.setText("sorry, something went wrong!");
                }

            }
        });
        bpost = (Button) findViewById(R.id.bpost);
        bpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OkHttpActivity.this, OkHttpPostActivity.class);
                startActivity(i);
            }
        });


    }

    public class OkHttpHandler extends AsyncTask<String, Void, byte[]> {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();


        @Override
        protected byte[] doInBackground(String... params) {
            Request builder = new Request.Builder()
                    .url(params[0])
                    .build();

            try {
                Response response = client.newCall(builder).execute();
                return response.body().bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
