package com.hansheng.studynote.RecyclerView.CustomRecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.RecyclerView.RecyclerViewDemo.GalleryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hansheng on 17-2-8.
 */

public class MainActivity extends AppCompatActivity{

    private MyRecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private ImageView mImg ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_customview_main);

        mImg = (ImageView) findViewById(R.id.id_content);

        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5));

        mRecyclerView = (MyRecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new GalleryAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener()
        {
            @Override
            public void onChange(View view, int position)
            {
                mImg.setImageResource(mDatas.get(position));
            };
        });

        mAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(View view, int position) {
                //              Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT)
//                      .show();
                mImg.setImageResource(mDatas.get(position));
            }
        });

    }
}
