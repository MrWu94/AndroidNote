package com.hansheng.studynote.Adapter.HSBaseAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 16-9-29.
 */

public class ListBaseAdapter extends AppCompatActivity {
    private List<String> mData = new ArrayList<>();
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = new ListView(this);
        setContentView(mListView);
        initData();
        showListView();
    }

    private void showListView() {
        mListView.setAdapter(new AuthorAdapter(mListView, mData, R.layout.recyclerview_item));
    }

    /**
     * 初始化测试数据
     */
    private void initData() {
        for (int i = 0; i < 20; i++) {
            mData.add("测试数据:" + i);
        }
    }
}
