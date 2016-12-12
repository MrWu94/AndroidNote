package com.hansheng.studynote.viewpager.pageradapterrefresh;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;
import com.hansheng.studynote.viewpager.pageradapterrefresh.adapter.FPagerAdapter1;

import java.util.LinkedList;
import java.util.List;

public class FPagerAdapter1Activity extends AppCompatActivity {
    private ViewPager mContentVP;

    private FPagerAdapter1 mPagerAdapter;
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

        mPagerAdapter = new FPagerAdapter1(getSupportFragmentManager(), mDataList);
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
        mPagerAdapter.updateData(mDataList);
    }

    private void add() {
        mDataList.add(7);
        mPagerAdapter.updateData(mDataList);
    }

    private void delete() {
        if (checkData()) return;
        mDataList.remove(0);
        mPagerAdapter.updateData(mDataList);
    }

    private void clear() {
        if (checkData()) return;
        mDataList.clear();
        mPagerAdapter.updateData(mDataList);
    }

    private boolean checkData() {
        return mDataList.size() == 0;
    }

}
