package com.hansheng.studynote.SQLDataBase.searchview;


import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yuandl on 2016-10-13.
 * 万能的ViewHolder
 *
 * @author
 */
public class CommonViewHolder {
    /**
     * 存储控件的集合
     */
    private SparseArray<View> sparseArray;
    /**
     * 当前item的position
     */
    private int position;

    /**
     * item的contentView
     *
     * @return
     */
    public View getContentView() {
        return contentView;
    }

    private View contentView;

    /**
     * @param context
     * @param position
     * @param layoutId
     * @return
     */

    public CommonViewHolder(Context context, int position, ViewGroup parent, int layoutId) {

        this.position = position;
        this.contentView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        sparseArray = new SparseArray<>();
        contentView.setTag(this);

    }

    public static CommonViewHolder getViewHolder(View convertView, int position) {
        CommonViewHolder commonViewHolder = (CommonViewHolder) convertView.getTag();
        commonViewHolder.position = position;
        return commonViewHolder;
    }

    public int getPosition() {
        return position;
    }

    /**
     * @param id 缓存View的唯一标识
     * @return
     */
    public <T extends View> T get(int id) {

        View chidlView = sparseArray.get(id);//获取根View储存在集合中的孩纸
        if (chidlView == null) {//如果没有改孩纸
            //找到该孩纸
            chidlView = contentView.findViewById(id);
            sparseArray.put(id, chidlView);//保存到集合
        }
        return (T) chidlView;
    }

    /**
     * @param view 所有缓存View的根View
     * @param id   缓存View的唯一标识
     * @return
     */
    public static <T extends View> T get(View view, int id) {

        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        //如果根view没有用来缓存View的集合
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);//创建集合和根View关联
        }
        View chidlView = viewHolder.get(id);//获取根View储存在集合中的孩纸
        if (chidlView == null) {//如果没有改孩纸
            //找到该孩纸
            chidlView = view.findViewById(id);
            viewHolder.put(id, chidlView);//保存到集合
        }
        return (T) chidlView;
    }
}
