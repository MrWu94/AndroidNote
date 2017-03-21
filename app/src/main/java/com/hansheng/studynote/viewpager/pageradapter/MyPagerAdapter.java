package com.hansheng.studynote.viewpager.pageradapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wfq on 2016/11/30.
 * * onPageScrollStateChanged(int arg0) ，此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。arg0 ==1表示正在滑动，
 * arg0==2表示滑动完毕了，arg0==0表示什么都没做。当页面开始滑动的时候，三种状态的变化顺序为（1，2，0）。
 * <p>
 * onPageScrolled(int arg0,float arg1,int arg2) ，当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用。
 * 其中三个参数的含义分别为：arg0 :当前页面，及你点击滑动的页面。arg1:当前页面偏移的百分比。arg2:当前页面偏移的像素位置。
 * <p>
 * onPageSelected(int arg0) ，此方法是页面跳转完后得到调用，arg0是你当前选中的页面的position。
 */

public class MyPagerAdapter extends PagerAdapter {
    private List<View> viewList;
    private List<String> titleList;

    public MyPagerAdapter(List<View> viewList, List<String> titleList) {
        this.viewList = viewList;
        this.titleList = titleList;
    }

    /**
     * 返回页卡的数量
     */
    @Override
    public int getCount() {

        return viewList.size();
    }

    /**
     * view是否来自同一对象
     */
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;
    }

    /**
     * 实例化一个页卡
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    /**
     * 销毁一个页卡
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(viewList.get(position));

    }

    /**
     * 设置ViewPager的标题
     */
    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }

}
