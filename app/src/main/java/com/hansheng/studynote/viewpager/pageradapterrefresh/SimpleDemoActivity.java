package com.hansheng.studynote.viewpager.pageradapterrefresh;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleDemoActivity extends AppCompatActivity {

    private ViewPager mContentVP;

    private PagerAdapter mPagerAdapter;
    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpledemo);

        initView();
        initData();
    }

    private void initView() {
        this.mContentVP = (ViewPager) findViewById(R.id.vp_content);
    }

    private void initData() {
        mDataList = new ArrayList<>(5);
        mDataList.add("Java");
        mDataList.add("Android");
        mDataList.add("C&C++");
        mDataList.add("OC");
        mDataList.add("Swift");

//        this.mContentVP.setAdapter(new DemoPagerAdapter(this, mDataList));
        this.mContentVP.setAdapter(mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mDataList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = View.inflate(SimpleDemoActivity.this, R.layout.item_vp_demopageradapter, null);
                TextView pageNumTV = (TextView) view.findViewById(R.id.tv_pagenum);
                pageNumTV.setText("DIY-PageNum-" + mDataList.get(position));
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_refresh:
                refresh();
                break;
            case R.id.btn_add:
                add();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_clean:
                clear();
                break;
        }
    }

    private void refresh() {
        if (checkData()) return;
        mDataList.set(0, "更新数据源测试");
        mPagerAdapter.notifyDataSetChanged();
    }

    private void add() {
        mDataList.add("这是新添加的Item");
        mPagerAdapter.notifyDataSetChanged();
    }

    private void delete() {
        if (checkData()) return;
        mDataList.remove(0);
        mPagerAdapter.notifyDataSetChanged();
    }

    private void clear() {
        if (checkData()) return;
        mDataList.clear();
        mPagerAdapter.notifyDataSetChanged();
    }

    private boolean checkData() {
        return mDataList.size() == 0;
    }

}
