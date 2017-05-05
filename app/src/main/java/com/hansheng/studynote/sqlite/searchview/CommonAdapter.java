package com.hansheng.studynote.sqlite.searchview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by yuandl on 2016-10-13.
 * 万能适配器
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> datas;
    private int layoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void notifyDataSetChanged(List<T> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            Log.d("listview", "---------LayoutInflater()-----------" + position);
//            convertView = LayoutInflater.from(context).inflate(layoutId, null);
//        }else{
//        }
////        Log.d("listview", "---------getView()-----------" + position);
//        T t = getItem(position);
//        convertView(convertView, t);
//        return convertView;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder;
        if (convertView == null) {
            commonViewHolder = new CommonViewHolder(context, position, parent, layoutId);
        } else {
            commonViewHolder = CommonViewHolder.getViewHolder(convertView, position);
        }
        convertView(commonViewHolder, getItem(position));
        return commonViewHolder.getContentView();
    }

    /**
     * 局部更新数据，调用一次getView()方法；Google推荐的做法
     *
     * @param listView 要更新的listview
     * @param position 要更新的位置
     */
    public void notifyDataSetChanged(ListView listView, int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();

        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            getView(position, view, listView);
        }

    }

    /**
     * 需要去实现的对item中的view的设置操作
     *
     * @param commonViewHolder
     * @param t
     */
    protected abstract void convertView(CommonViewHolder commonViewHolder, T t);

}
