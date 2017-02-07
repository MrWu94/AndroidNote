package com.hansheng.studynote.viewpager.MusicViewPager;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RadioButton;

import com.hansheng.studynote.R;
import com.hansheng.studynote.widget.BActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by hansheng on 16-9-21.
 * onPageScrollStateChanged(int arg0) ，此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。arg0 ==1表示正在滑动，
 * arg0==2表示滑动完毕了，arg0==0表示什么都没做。当页面开始滑动的时候，三种状态的变化顺序为（1，2，0）。
 *
 * onPageScrolled(int arg0,float arg1,int arg2) ，当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用。
 * 其中三个参数的含义分别为：arg0 :当前页面，及你点击滑动的页面。arg1:当前页面偏移的百分比。arg2:当前页面偏移的像素位置。
 *
 * onPageSelected(int arg0) ，此方法是页面跳转完后得到调用，arg0是你当前选中的页面的position。
 */

public class MusicActivity extends BActivity {
    static final int DEFAULT_PAGE_INDEX = 2;
    private static final String TAG="MusicActivity";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind({R.id.radio_button_play_list, R.id.radio_button_music, R.id.radio_button_local_files, R.id.radio_button_settings})
    List<RadioButton> radioButtons;

    String[] mTitles;

    static
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_main);
        ButterKnife.bind(this);

        // Main Controls' Titles
        mTitles = getResources().getStringArray(R.array.mp_main_titles);

        // Fragments
        Fragment[] fragments = new Fragment[mTitles.length];
        fragments[0] = new PlayListFragment();
        fragments[1] = new MusicPlayerFragment();
        fragments[2] = new LocalFilesFragment();
        fragments[3] = new SettingsFragment();


        // Inflate ViewPager
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), mTitles, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.mp_margin_large));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: position="+position+"  positionOffset="+positionOffset+" positionOffsetPixels="+positionOffsetPixels);
                // Empty
//                viewPager.getChildAt(position);
//                viewPager.getCurrentItem();


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: state"+state);
                // Empty
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: position"+position);
                radioButtons.get(position).setChecked(true);
            }
        });

        radioButtons.get(DEFAULT_PAGE_INDEX).setChecked(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @OnCheckedChanged({R.id.radio_button_play_list, R.id.radio_button_music, R.id.radio_button_local_files, R.id.radio_button_settings})
    public void onRadioButtonChecked(RadioButton button, boolean isChecked) {
        if (isChecked) {
            onItemChecked(radioButtons.indexOf(button));
        }
    }

    private void onItemChecked(int position) {
        viewPager.setCurrentItem(position);
        toolbar.setTitle(mTitles[position]);
    }
}
