package com.hansheng.studynote.viewpager.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.hansheng.studynote.R;
import com.hansheng.studynote.fragment.ListFragement.Fragment1;
import com.hansheng.studynote.fragment.ListFragement.Fragment2;
import com.hansheng.studynote.viewpager.fragmentpageradapter.MyFragmentPagerAdapter;
import com.hansheng.studynote.viewpager.fragmentpagerstagepager.MyFragmentPagerAdapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wfq on 2016/11/30.
 */

public class PagerAdapterActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
    private List<View> viewList;
    private List<String> titleList;
    private ViewPager pager;
    private PagerTabStrip tab;//顶部标题

    private List<Fragment> fragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_main);
        viewList = new ArrayList<View>();
        titleList = new ArrayList<String>();
        tab = (PagerTabStrip) findViewById(R.id.tab);

        View view1 = View.inflate(this, R.layout.page1, null);
        View view2 = View.inflate(this, R.layout.page2, null);
        View view3 = View.inflate(this, R.layout.page3, null);
        View view4 = View.inflate(this, R.layout.page4, null);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        fragList = new ArrayList<Fragment>();
        fragList.add(new Fragment1());
        fragList.add(new Fragment2());
//        fragList.add(new Fragment1());
//        fragList.add(new Fragment2());

        //为ViewPager页卡设置标题
        titleList.add("第一页");
        titleList.add("第二页");
//        titleList.add("第三页");
//        titleList.add("第四页");
        //为PagerTabStrip设置一些属性
        tab.setBackgroundColor(Color.WHITE);
        tab.setDrawFullUnderline(false);
        tab.setTabIndicatorColor(Color.BLUE);

        pager = (ViewPager) findViewById(R.id.pager);
//        MyPagerAdapter adapter = new MyPagerAdapter(viewList,titleList);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragList, titleList);
//        /**
//         * 使用getSupportFragmentManager()该Activity必须继承FragmentActivity
//         */
//        MyFragmentPagerAdapter2 adapter = new MyFragmentPagerAdapter2(getSupportFragmentManager(), fragList, titleList);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        Toast.makeText(this, "当前是第" + (arg0 + 1) + "个界面", 0).show();

    }

}


