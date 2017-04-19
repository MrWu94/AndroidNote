package com.hansheng.studynote.Listview.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 16-12-12.
 */

public class MutileItemActivity extends AppCompatActivity {
    private News news;
    private List<String> imageUrl;
    private List<News> newsList;

    private RecyclerView recyclerView;
    private MutileItemAdapter mutileItemAdapter;
    private Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        mContext=getApplicationContext();
        news = new News();
        imageUrl = new ArrayList<>();
        newsList = new ArrayList<>();
        imageUrl.add("hansheng");
        news.setImageUrl(imageUrl);
        news.setTitle("android");
        newsList.add(news);
        setImageUrl();
        mutileItemAdapter=new MutileItemAdapter(mContext,newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mutileItemAdapter);
    }

    public void setImageUrl() {
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 2) {
                imageUrl.add("IOS" + i);
            }else{
                imageUrl.add("java");
            }

            news.setImageUrl(imageUrl);
            news.setTitle("android");
            newsList.add(news);
        }
    }
}
