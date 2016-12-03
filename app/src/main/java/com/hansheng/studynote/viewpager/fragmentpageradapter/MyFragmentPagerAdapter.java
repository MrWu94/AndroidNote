package com.hansheng.studynote.viewpager.fragmentpageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wfq on 2016/11/30.
 * <p>
 * 让Fragment 成为ViewPager的一页时，FragmentManager会一直保存管理创建好了的Fragment，即使当前不是显示的这一页
 * ，Fragment对象也不会被销毁，在后台默默等待重新显示。但如果Fragment不再可见时，它的视图层次会被销毁掉，下次显示时视图会重新创建。
 * <p>
 * 出于使用FragmentPagerAdapter  时，Fragment对象会一直存留在内存中，所以当有大量的显示页时，就不适合用FragmentPagerAdapter
 * 了，FragmentPagerAdapter  适用于只有少数的page情况，像选项卡。这个时候你可以考虑使用FragmentStatePagerAdapter   ，
 * 当使用FragmentStatePagerAdapter  时，如果Fragment不显示，那么Fragment对象会被销毁，但在回调onDestroy()方法之前会回调
 * onSaveInstanceState(Bundle outState)方法来保存Fragment的状态，下次Fragment显示时通过onCreate(Bundle savedInstanceState)
 * 把存储的状态值取出来，FragmentStatePagerAdapter  比较适合页面比较多的情况，像一个页面的ListView
 * 使用FragmentPagerAdapter 适配器时，创建好了的Fragment会一直在内存中，不会被销毁，但它的视图层次是会被销毁的，
 * 所以onCreate()方法只会被调用一次，而 onCreateView() 方法，每次Fragment从不可见到可见时会被调用，可以看到Fragment有一
 * 些生命周期回调方法 onPause()、onDestroy()等等
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragList;
    private List<String> titleList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragList, List<String> titleList) {
        super(fm);
        this.fragList = fragList;
        this.titleList = titleList;
    }


    @Override
    public Fragment getItem(int arg0) {

        return fragList.get(arg0);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }

    @Override
    public int getCount() {

        return fragList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 这里Destroy的是Fragment的视图层次，并不是Destroy Fragment对象
        super.destroyItem(container, position, object);
        Log.i("INFO", "Destroy Item...");
    }
}
