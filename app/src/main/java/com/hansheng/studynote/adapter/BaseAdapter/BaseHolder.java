package com.hansheng.studynote.adapter.BaseAdapter;

import android.view.View;

/**
 * Created by hansheng on 16-9-29.
 */
public abstract class BaseHolder<T> {
    private View mRootView;
    private T mData;

    public BaseHolder() {
        //初始化item的根布局
        mRootView = initItemLayout();
        //绑定holder到当前根布局中
        mRootView.setTag(this);
    }


    /**
     * 获取item的根布局
     *
     * @return
     */
    public View getConvertView() {
        return mRootView;
    }

    /**
     * 设置item的数据
     *
     * @param t 具体的数据
     */
    public void setData(T t) {
        this.mData = t;
        refreshView(t);
    }

    /**
     * 获取显示的数据
     *
     * @return
     */
    public T getmData() {
        return mData;
    }

    /**
     * 由子类去实现刷新界面数据的方法
     *
     * @param t 具体的数据
     */
    public abstract void refreshView(T t);

    /**
     * 由具体的子类去实现初始化listView Item的根布局
     *
     * @return
     */
    public abstract View initItemLayout();
}