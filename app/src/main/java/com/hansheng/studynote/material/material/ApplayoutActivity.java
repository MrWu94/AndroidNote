package com.hansheng.studynote.material.material;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.material.materialdesign.fragment.AuthorInfoFragment;
import com.hansheng.studynote.ui.activity.BaseActivity;

import butterknife.Bind;

/**
 * Created by hansheng on 17-5-20.
 */

public class ApplayoutActivity extends BaseActivity {

    private String url;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.iv_book_image)
    ImageView mIvBook;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_rating)
    TextView mTvRating;
    @Bind(R.id.tv_msg)
    TextView mTvMsg;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.sliding_tabs)
    TabLayout mTabLayout;

    private Fragment[] mFragmentArrays = new Fragment[3];
    private String[] mTabTitles = new String[3];


    @Override
    protected int initContentView() {
        return R.layout.activity_applayout;
    }

    @Override
    protected void initView() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTabTitles[0] = "Clock";
        mTabTitles[1] = "Dannie";
        mTabTitles[2] = "Lufy";

        mCollapsingToolbarLayout.setTitle("胡歌");
        mTvMsg.setText(R.string.use_recite_or_nor_summary);
        mTabLayout.setupWithViewPager(mViewPager);

        mFragmentArrays[0] = AuthorInfoFragment.newInstance();
        mFragmentArrays[1] = AuthorInfoFragment.newInstance();
        mFragmentArrays[2] = AuthorInfoFragment.newInstance();

        PagerAdapter pagerAdapter = new AuthorPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        mTabLayout.setTabsFromPagerAdapter(pagerAdapter);

    }

    private class AuthorPagerAdapter extends FragmentPagerAdapter {

        public AuthorPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }

        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }

}
