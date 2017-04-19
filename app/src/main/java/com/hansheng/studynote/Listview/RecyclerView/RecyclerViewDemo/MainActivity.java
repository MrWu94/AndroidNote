package com.hansheng.studynote.Listview.RecyclerView.RecyclerViewDemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by hansheng on 17-2-8.
 */

public class MainActivity extends BaseActivity {

    @Bind(R.id.id_recyclerview)
    RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;

    @Override
    protected int initContentView() {
        return R.layout.recyclerview_demo_main;


    }

    @Override
    protected void initView() {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new GalleryAdapter(this, mDatas);
        mAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }
}
