package com.hansheng.studynote.viewpager.PageTransformer;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by hansheng on 17-2-7.
 */

public class MainActivity extends BaseActivity {
    @Bind(R.id.transformer_viewpager)
    ViewPager mViewPager;

    private int[] mImgIds = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};


    private List<ImageView> mImageViews = new ArrayList<ImageView>();


    @Override
    protected int initContentView() {
        return R.layout.pagetransformer_main;
    }

    @Override
    protected void initView() {
        initData();
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImageViews.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mImageViews.get(position));
                return mImageViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViews.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

    }

    private void initData() {
        for (int imgId : mImgIds) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgId);
            mImageViews.add(imageView);
        }

    }
}
