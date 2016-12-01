package com.hansheng.studynote.viewpager.fragmentpageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wfq on 2016/11/30.
 * FragmentPagerAdapter
 * <p>
 * 适合于 Fragment数量不多的情况。当某个页面不可见时，该页面对应的View可能会被销毁，但是所有的Fragment都会一直存在于内存中。
 * 如果Fragment需要保存的状态较多时，
 * 会导致占用内存较大，因此对于Fragment数量较多的情况，建议使用FragmentStatePagerAdapter。
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragList;
    private List<String> titleList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragList, List<String> titleList) {
        super(fm);
        this.fragList = fragList;
        this.titleList = titleList;
    }


    @Override
    public Fragment getItem(int arg0) {

        return fragList.get(arg0);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }

    @Override
    public int getCount() {

        return fragList.size();
    }
}
