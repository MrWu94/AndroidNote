package com.hansheng.studynote.http.volleydemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.hansheng.studynote.R;


/**
 * Created by hansheng on 2016/6/28.
 */
public class VolleyImageActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_layout);
        button= (Button) findViewById(R.id.button);
        imageView= (ImageView) findViewById(R.id.image2);
        requestQueue= Volley.newRequestQueue(getApplicationContext());

        ImageLoader imageLoader=new ImageLoader(requestQueue, new MemoryCache());

        ImageLoader.ImageListener listener=imageLoader.getImageListener(imageView,R.mipmap.ic_launcher,R.mipmap.ic_launcher);

        imageLoader.get("http://tupian.enterdesk.com/2012/0615/gha/15/1337566102yPLeJh.jpg",listener);

    }
}
