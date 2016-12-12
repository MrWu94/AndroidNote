package com.hansheng.studynote.viewpager.pageradapterrefresh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.hansheng.studynote.viewpager.pageradapterrefresh.fragment.FragmentTest;

import java.util.ArrayList;
import java.util.List;

public class FSPagerAdapter extends FragmentStatePagerAdapter {

	private ArrayList<Fragment> mFragmentList;

	public FSPagerAdapter(FragmentManager fm, List<Integer> types) {
		super(fm);
		updateData(types);
	}

	public void updateData(List<Integer> dataList) {
		ArrayList<Fragment> fragments = new ArrayList<>();
		for (int i = 0, size = dataList.size(); i < size; i++) {
			Log.e("FPagerAdapter1", dataList.get(i).toString());
			fragments.add(FragmentTest.instance(dataList.get(i)));
		}
		setFragmentList(fragments);
	}

	private void setFragmentList(ArrayList<Fragment> fragmentList) {
		if(this.mFragmentList != null){
			mFragmentList.clear();
		}
		this.mFragmentList = fragmentList;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return this.mFragmentList.size();
	}
	
	public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
		return mFragmentList.get(position);
    }
}
