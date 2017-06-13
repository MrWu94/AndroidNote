package com.hansheng.studynote.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hansheng on 2016/7/18.
 */
public class NestActivity extends AppCompatActivity {
    private NestedListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nesting_scrollview_listview);
        listView = (NestedListView) findViewById(R.id.listview1);
        //生成动态数组，并且转载数据
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 30; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", "This is Title.....");
            map.put("ItemText", "This is text.....");
            mylist.add(map);
        }
        //生成适配器，数组===》ListItem
        SimpleAdapter mSchedule = new SimpleAdapter(this, //没什么解释
                mylist,//数据来源
                R.layout.my_listitem,//ListItem的XML实现

                //动态数组与ListItem对应的子项
                new String[]{"ItemTitle", "ItemText"},

                //ListItem的XML文件里面的两个TextView ID
                new int[]{R.id.ItemTitle, R.id.ItemText});
        TextView header = new TextView(this);
        header.setText("我是头部");
        LinearLayout headerParent = new LinearLayout(this);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        headerParent.addView(header, mLayoutParams);
        listView.addHeaderView(headerParent);
        listView.setAdapter(mSchedule);
        TextView footer = new TextView(this);
        footer.setText("我是尾部");
        LinearLayout footerParent = new LinearLayout(this);
        footerParent.addView(footer, mLayoutParams);
        listView.addFooterView(footerParent);

    }
}
