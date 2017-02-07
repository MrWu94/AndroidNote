package com.hansheng.studynote.viewpager.MyViewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-2-7.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private int[] mImgIds;

    private MyJazzyViewPager mViewPager;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myviewpager_main);

        mImgIds=new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};

        mViewPager= (MyJazzyViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImgIds.length;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(mImgIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mViewPager.setObjectForPosition(imageView, position);
                return imageView;
            }



            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        });
    }
}
