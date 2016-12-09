package com.hansheng.studynote.viewpager.pageradapterrefresh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.hansheng.studynote.viewpager.pageradapterrefresh.fragment.FragmentTest;

import java.util.ArrayList;
import java.util.List;

public class FPagerAdapter2 extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private List<Integer> mDataList;
    private List<String> mTagList;
    private boolean isDataSetChange;

    public FPagerAdapter2(FragmentManager fragmentManager, List<Integer> dataList) {
        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
        this.mDataList = dataList;
        this.mTagList = new ArrayList<>();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "DIY-" + mDataList.get(position);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentTest.instance(mDataList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mTagList.add(position, makeFragmentName(container.getId(),
                (int) getItemId(position)));
        return super.instantiateItem(container, position);
    }

    @Override
    public int getItemPosition(Object object) {
        // 解决数据源清空，Item 不销毁的 bug
        return isDataSetChange || (mDataList != null && mDataList.size()==0) ? POSITION_NONE : super.getItemPosition(object);
    }

    // FragmentPageAdapter源码里给 Fragment 生成标签的方法
    private String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    public void update(int position, String str) {
        Fragment fragment = mFragmentManager.findFragmentByTag(mTagList.get(position));
        if (fragment == null) return;
        if (fragment instanceof FragmentTest) {
            ((FragmentTest)fragment).update(str);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDataList.remove(position);
        isDataSetChange = true;
        Fragment fragment = mFragmentManager.findFragmentByTag(mTagList.get(position));
        mTagList.remove(position);
        if (fragment == null) {
            notifyDataSetChanged();
            return;
        }
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
        mFragmentManager.executePendingTransactions();
        notifyDataSetChanged();
    }

}