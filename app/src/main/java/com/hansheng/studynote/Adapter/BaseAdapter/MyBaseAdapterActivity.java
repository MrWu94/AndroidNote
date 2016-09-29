package com.hansheng.studynote.Adapter.BaseAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 16-9-29.
 */

public class MyBaseAdapterActivity extends AppCompatActivity {
    private List<String> mData = new ArrayList<>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = new ListView(this);
        setContentView(mListView);
        initData();
        showListView();
    }

    /**
     * 显示ListView
     */
    private void showListView() {
        mListView.setAdapter(new MyBaseAdapter<String>(mData) {
            @Override
            public BaseHolder getBaseHolder(int position) {

                return new BaseHolder<String>() {
                    private TextView tvInfo;

                    @Override
                    public View initItemLayout() {
                        View view = View.inflate(MyBaseAdapterActivity.this, R.layout.base_layout, null);
                        tvInfo = (TextView) view.findViewById(R.id.id_tv_info);
                        return view;
                    }

                    @Override
                    public void refreshView(String s) {
                        tvInfo.setText(s);
                    }
                };
            }
        });
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
