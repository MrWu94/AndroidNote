package com.hansheng.studynote.scroller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.util.ArrayList;

/**
 * Created by hansheng on 2016/7/5.
 */
public class HozizontalActivity extends AppCompatActivity {
    
    private static final String TAG="horizontalactivity";
    private HorizontalScrollViewEx mListContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_layout);
        initView();
    }

    private void initView() {
        LayoutInflater inflater=getLayoutInflater();
        mListContainer= (HorizontalScrollViewEx) findViewById(R.id.container);
        final int screenWidth=MyUtils.getScreenMetrics(this).widthPixels;
        final int screenHeight=MyUtils.getScreenMetrics(this).heightPixels;
        for(int i=0;i<3;i++){
            ViewGroup layout= (ViewGroup) inflater.inflate(R.layout.content_layout,mListContainer,false);
            layout.getLayoutParams().width=screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);

        }
    }
    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(HozizontalActivity.this, "click item",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}
