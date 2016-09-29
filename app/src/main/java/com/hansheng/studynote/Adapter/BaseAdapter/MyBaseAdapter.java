package com.hansheng.studynote.Adapter.BaseAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hansheng.studynote.material.BaseActivity;

import java.util.List;

/**
 * Created by hansheng on 16-9-29.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> mData;

    public MyBaseAdapter(List<T> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData == null ? (T) null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (null == convertView) {
            holder = getBaseHolder(position);
            convertView = holder.getConvertView();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        holder.setData(getItem(position));
        return convertView;
    }

    /**
     * 由子类去实现获取BaseHolder的方法
     *
     * @param position
     * @return BaseHolder
     */
    public abstract BaseHolder getBaseHolder(int position);
}