package com.hansheng.studynote.imageordrawable.photowall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/6/28.
 */
public class photoActivity extends AppCompatActivity {

    private GridView mPhotoWall;

    private PhotoWallAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);
        mPhotoWall= (GridView) findViewById(R.id.photo_wall);
        adapter=new PhotoWallAdapter(this,0,Images.imageThumbUrls,mPhotoWall);
        mPhotoWall.setAdapter(adapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        adapter.cancelAllTasks();
    }
}
