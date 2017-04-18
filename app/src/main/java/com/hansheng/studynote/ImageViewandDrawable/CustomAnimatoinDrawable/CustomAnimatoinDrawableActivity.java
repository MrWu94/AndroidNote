package com.hansheng.studynote.ImageViewandDrawable.CustomAnimatoinDrawable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hansheng on 17-4-12.
 */

public class CustomAnimatoinDrawableActivity extends AppCompatActivity {

    private ImageView imageView;
    private List<Integer> mResourceIdList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_animation_drawable);
        imageView = (ImageView) findViewById(R.id.iv_play_main);
//        mResourceIdList = new ArrayList<>();

        mResourceIdList = Arrays.asList(R.drawable.ic_me_title_logo_one, R.drawable.ic_me_title_logo_two, R.drawable.ic_me_title_logo_three, R.drawable.ic_me_title_logo_four
                , R.drawable.ic_me_title_logo_five, R.drawable.ic_me_title_logo_six, R.drawable.ic_me_title_logo_seven,
                R.drawable.ic_me_title_logo_eight, R.drawable.ic_me_title_logo_nine, R.drawable.ic_me_title_logo_ten,
                R.drawable.ic_me_title_logo_eleven, R.drawable.ic_me_title_logo_twelve
        );
//        mResourceIdList.add(0, R.drawable.ic_me_title_logo_one);
//        mResourceIdList.add(1, R.drawable.ic_me_title_logo_two);
//        mResourceIdList.add(2, R.drawable.ic_me_title_logo_three);
//        mResourceIdList.add(3, R.drawable.ic_me_title_logo_four);
//        mResourceIdList.add(4, R.drawable.ic_me_title_logo_five);
//        mResourceIdList.add(5, R.drawable.ic_me_title_logo_six);
//        mResourceIdList.add(6, R.drawable.ic_me_title_logo_seven);
//        mResourceIdList.add(7, R.drawable.ic_me_title_logo_eight);
//        mResourceIdList.add(8, R.drawable.ic_me_title_logo_nine);
//        mResourceIdList.add(9, R.drawable.ic_me_title_logo_ten);
//        mResourceIdList.add(10, R.drawable.ic_me_title_logo_eleven);
//        mResourceIdList.add(11, R.drawable.ic_me_title_logo_twelve);
        new AnimImageView().setAnimation(imageView, mResourceIdList);


    }
}
