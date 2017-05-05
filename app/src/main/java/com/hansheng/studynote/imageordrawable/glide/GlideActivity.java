package com.hansheng.studynote.imageordrawable.glide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hansheng.studynote.R;

import static com.hansheng.studynote.R.mipmap.ic_launcher;

/**
 * Created by hansheng on 2016/9/24.
 */


public class GlideActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView imageRec;
    private ImageView imageGif;
    private ImageView imageGifBitmap;
    String gifUrl = "http://i.kinja-img.com/gawker-media/image/upload/s--B7tUiM5l--/gf2r69yorbdesguga10i.gif";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_layout);
        imageRec = (ImageView) findViewById(R.id.img_resource);
        imageView = (ImageView) findViewById(R.id.glide_iamgeview);
        imageGif = (ImageView) findViewById(R.id.img_gif);
        imageGifBitmap = (ImageView) findViewById(R.id.img_gifbitmap);
        Glide.with(this).load("https://avatars0.githubusercontent.com/u/12311938?v=3&s=40")
                .placeholder(ic_launcher)
                .transform(new GlideCircleTransform(this))
                .into(imageView);

        int resourceId = R.mipmap.ic_launcher;

        Glide.with(this).load(resourceId).into(imageRec);
        Glide.with(this).load(gifUrl)
                .into(imageGif);
        Glide.with(this).load(gifUrl)
                .asBitmap()
                .into(imageGifBitmap);

        ImageLoader.load(getApplicationContext(),"https://avatars0.githubusercontent.com/u/12311938?v=3&s=40",imageView);


    }
}
