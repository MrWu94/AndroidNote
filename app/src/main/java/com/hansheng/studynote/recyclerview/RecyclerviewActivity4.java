package com.hansheng.studynote.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-29.
 */

public class RecyclerviewActivity4 extends AppCompatActivity {
    private Activity activity;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);
        activity = this;
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
//        recyclerview.setLayoutManager(new LinearLayoutManager(this,
//                LinearLayoutManager.VERTICAL, false));
//        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(gridLayoutManager);
        final Demo5Adapter adapter = new Demo5Adapter(this);
        recyclerview.setAdapter(adapter);

//        recyclerview.addOnItemTouchListener(new RecyclerItemClickListener(activity) {
//            @Override
//            protected void onItemClick(View view, int position) {
//                Toast.makeText(activity, "点击了" + position, Toast.LENGTH_SHORT).show();
//            }
//        });

        //设置头部及底部View占据整行空间
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isFeatureItem(position) == 1 ? gridLayoutManager.getSpanCount() : 1;
            }
        });
    }
}
