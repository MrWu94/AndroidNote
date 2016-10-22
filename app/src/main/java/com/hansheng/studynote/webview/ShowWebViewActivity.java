package com.hansheng.studynote.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-10-22.
 */

public class ShowWebViewActivity  extends AppCompatActivity {
    private TextView imageTextView = null;
    private ImageView img;
    private String imagePath = null;

    public static void startAction(Context context, String image){
        Intent intent = new Intent(context,ShowWebViewActivity.class);
        intent.putExtra("image",image);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_big_photo);
        this.imagePath = getIntent().getStringExtra("image");

        this.imageTextView = (TextView) findViewById(R.id.tv_url);
        img= (ImageView) findViewById(R.id.img);

        imageTextView.setText(this.imagePath);
        Glide.with(this).load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f).into(img);
    }

}