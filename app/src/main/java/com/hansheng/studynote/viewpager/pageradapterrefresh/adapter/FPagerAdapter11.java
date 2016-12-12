package com.hansheng.studynote.viewpager.pageradapterrefresh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.hansheng.studynote.viewpager.pageradapterrefresh.fragment.FragmentTest;

import java.util.ArrayList;
import java.util.List;

public class FPagerAdapter11 extends FragmentStatePagerAdapter {

	private ArrayList<Fragment> mFragmentList;
	private FragmentManager mFragmentManager;

	public FPagerAdapter11(FragmentManager fm, List<Integer> types) {
		super(fm);
		this.mFragmentManager = fm;
		mFragmentList = new ArrayList<>();
		for (int i = 0, size = types.size(); i < size; i++) {
			mFragmentList.add(FragmentTest.instance(i));
		}
		setFragments(mFragmentList);
	}

	public void updateData(List<Integer> dataList) {
		ArrayList<Fragment> fragments = new ArrayList<>();
		for (int i = 0, size = dataList.size(); i < size; i++) {
			Log.e("FPagerAdapter1", dataList.get(i).toString());
			fragments.add(FragmentTest.instance(dataList.get(i)));
		}
		setFragments(fragments);
	}

	private void setFragments(ArrayList<Fragment> mFragmentList) {
		if(this.mFragmentList != null){
			FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
			for(Fragment f:this.mFragmentList){
				fragmentTransaction.remove(f);
			}
			fragmentTransaction.commit();
			mFragmentManager.executePendingTransactions();
		}
		this.mFragmentList = mFragmentList;
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
