package com.hansheng.studynote.listview.GridView.GridViewandViewPager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hansheng on 16-12-7.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;

    public ViewPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


//        try {
//            if (mViewList.get(position).getParent() == null) {
//                ((ViewPager) container).addView(mViewList.get(position), 0);
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

//        ViewGroup parent = (ViewGroup) container.getParent();
//        if (parent != null) {
//            parent.removeAllViews();
//        }
//        container.addView(mViewList.get(position));
//        return mViewList.get(position);

//        position %= mViewList.size();
//        if (position<0){
//            position = mViewList.size()+position;
//        }
//        View view = mViewList.get(position);
//        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
//        ViewParent vp =view.getParent();
//        if (vp!=null){
//            ViewGroup parent = (ViewGroup)vp;
//            parent.removeView(view);
//        }
//        container.addView(view);
//        //add listeners here if necessary
//        return view;

        container.addView(mViewList.get(position));
        return (mViewList.get(position));
    }

    @Override
    public int getCount() {
        if (mViewList == null)
            return 0;
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}