package com.hansheng.studynote.viewpager.pageradapterrefresh;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;
import com.hansheng.studynote.viewpager.pageradapterrefresh.adapter.FPagerAdapter2;

import java.util.LinkedList;
import java.util.List;

public class FPagerAdapter2Activity extends AppCompatActivity {

    private ViewPager mContentVP;

    private FPagerAdapter2 mPagerAdapter;
    private List<Integer> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageradapter);

        initView();
        initData();
    }

    private void initView() {
        this.mContentVP = (ViewPager) findViewById(R.id.vp_content);
    }

    private void initData() {
        mDataList = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            mDataList.add(i);
        }

        mPagerAdapter = new FPagerAdapter2(getSupportFragmentManager(), mDataList);
        mContentVP.setAdapter(mPagerAdapter);
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
        mDataList.set(0, 7);
        mPagerAdapter.update(0, "更新数据源测试");
    }

    private void add() {
        mDataList.add(7);
        mPagerAdapter.notifyDataSetChanged();
    }

    private void delete() {
        if (checkData()) return;
        mPagerAdapter.remove(0);
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
