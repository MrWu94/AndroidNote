/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.hansheng.studynote.viewpager.pageradapterrefresh.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description: 简单封装 PagerAdapter
 * <br/>Program Name:
 * <br/>Date: 2016年3月7日

 */
public abstract class APagerAdapter<T> extends PagerAdapter {

    protected LayoutInflater mInflater;
    protected List<T> mDataList;
    protected SparseArray<View> mViewSparseArray;

    public APagerAdapter(Context context, List<T> dataList) {
        mInflater = LayoutInflater.from(context);
        mDataList = dataList;
        mViewSparseArray = new SparseArray<View>(dataList.size());
    }

    @Override
    public int getCount() {
        if (mDataList == null) return 0;
        return mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViewSparseArray.get(position);
        if (view == null) {
            view = getView(position);
            mViewSparseArray.put(position, view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(mViewSparseArray.get(position));
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return mDataList != null && mDataList.size()==0 ? POSITION_NONE : super.getItemPosition(object);
    }

    public abstract View getView(int position);

    public T getItem(int position) {
        return mDataList.get(position);
    }

}