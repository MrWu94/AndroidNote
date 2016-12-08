package com.hansheng.studynote.fragment.viewpagerandfragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wfq on 2016/12/5.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"tab1", "tab2", "tab3", "tab4"};
    private Fragment fragment[] = new Fragment[]{new Fragment_1(), new Fragment_2(), new Fragment_3(), new Fragment_4()};
    private List<Fragment> fragments;
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
