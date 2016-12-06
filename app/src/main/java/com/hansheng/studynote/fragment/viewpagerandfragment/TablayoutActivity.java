package com.hansheng.studynote.fragment.viewpagerandfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wfq on 2016/12/5.
 */

public class TablayoutActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        tabLayout = (TabLayout) findViewById(R.id.tab_main_activity);
        viewPager = (ViewPager) findViewById(R.id.vp_main_activity);


        fragments=new ArrayList<>();
        fragments.add(new Fragment_1());
        fragments.add(new Fragment_2());
        fragments.add(new Fragment_3());
        fragments.add(new Fragment_4());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),getApplicationContext(),fragments);
//        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);

//        List<Fragment> data = new ArrayList<>();;
//        data.add(new Fragment_1());
//        data.add(new Fragment_2());
//        data.add(new Fragment_3());
//        data.add(new Fragment_4());
//
//        adapter.setFragmentList(data);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0 ; i < adapter.getCount() ; i ++){
            tabLayout.getTabAt(i).setText("Tab_"+(i+1));
        }
    }
}
