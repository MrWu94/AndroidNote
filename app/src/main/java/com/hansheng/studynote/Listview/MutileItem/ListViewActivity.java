package com.hansheng.studynote.Listview.MutileItem;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 16-12-14.
 */

public class ListViewActivity extends AppCompatActivity {
    private ListView listView;
    private MyListAdapter myListAdapter;
    private ViewHolderAdapter viewHolderAdapter;
    private List<String> listData;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        initData();
        context=getApplicationContext();
        myListAdapter=new MyListAdapter(context, listData);
        viewHolderAdapter=new ViewHolderAdapter(context, listData);
        listView= (ListView) findViewById(R.id.list_view);
        listView.setAdapter(viewHolderAdapter);

    }

    private void initData() {
        listData = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            listData.add("这是第 " + i + " 条数据");
        }
    }
}
