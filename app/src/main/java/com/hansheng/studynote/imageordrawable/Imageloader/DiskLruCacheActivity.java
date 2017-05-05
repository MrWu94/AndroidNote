package com.hansheng.studynote.imageordrawable.Imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hansheng on 2016/6/29.
 */
public class DiskLruCacheActivity extends AppCompatActivity {

    private DiskLruCache mDiskLruCache=null;
    private ImageView imageview;
    String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
    String key = DiskLruCacheUtil.hashKeyForDisk(imageUrl);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disklrucache_layout);
        imageview= (ImageView) findViewById(R.id.imageview);

        try {
            File cacheDir = DiskLruCacheUtil.getDiskCacheDir(getApplication(), "bitmap");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, DiskLruCacheUtil.getAppVersion(getApplicationContext()), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String key = DiskLruCacheUtil.hashKeyForDisk(imageUrl);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                imageview.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String key = DiskLruCacheUtil.hashKeyForDisk(imageUrl);
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (downloadUrlToStream(imageUrl, outputStream)) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                    }
                    mDiskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private boolean downloadUrlToStream(String urlString, OutputStream outputStream){

        HttpURLConnection urlConnection=null;
        BufferedInputStream in=null;
        BufferedOutputStream out=null;
        try {
            final URL url=new URL(urlString);
            urlConnection= (HttpURLConnection) url.openConnection();
            in=new BufferedInputStream(urlConnection.getInputStream(),8*1024);
            out=new BufferedOutputStream(outputStream,8*1024);
            int b;
            while((b=in.read())!=-1){
                out.write(b);
            }
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }

                try {
                    if(out!=null) {
                        out.close();
                    }
                    if(in!=null){
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

        return false;
    }
}
