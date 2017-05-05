package com.hansheng.studynote.adapter.HSBaseAdapter;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hansheng on 16-9-29.
 * 封装ViewHolder
 * 只看getView，其他方法都一样；首先调用ViewHolder的get方法，如果convertView为null，new一个ViewHolder实例，通过使用mInflater.
 * inflate加载布局，然后new一个HashMap用于存储View，最后setTag(this)； 如果存在那么直接getTag最后通过getView(id)获取控件
 * ，如果存在则直接返回，否则调用findViewById，返回存储，返回。
 */

public class AdapterHolder {
    private final SparseArray<View> mViews;
    private final int mPosition;
    private final View mConvertView;

    private AdapterHolder(ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(
                layoutId, parent, false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到全部View
     *
     * @return
     */
    public SparseArray<View> getAllView() {
        return mViews;
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static AdapterHolder get(View convertView, ViewGroup parent,
                                    int layoutId, int position) {
        if (convertView == null) {
            return new AdapterHolder(parent, layoutId, position);
        } else {
            return (AdapterHolder) convertView.getTag();
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     * 首先是判断converView是否空，然后载入item布局，然后ViewHolder挨个初始化控件，然后通过tag保存holder，最后设置View的显示。
     * 步棸都知道了，那么我们慢慢来观察：ViewHolder一定是包含了item子控件的一个静态类。那么我们就干脆把item所有的子控件都
     * 放到ViewHolder里面，但是既然我们要通用，item肯定不是固定的，这就没办法把ViewHolder写的像上面的那种属性的形式。
     * 这里我们使用一个键值对来存储Map<id, view>全部的控件，这样就可以在需要的时候直接通过id来找到对应的子View了。
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public AdapterHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public AdapterHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public AdapterHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public AdapterHolder setImageByUrl(Bitmap bitmap, int viewId, String url) {
//        bitmap.display(getView(viewId), url);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

}