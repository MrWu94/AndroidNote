package com.hansheng.studynote.Gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by wfq on 2016/11/11.
 */

public class HorizontalScrollViewAcrtivity extends AppCompatActivity {
    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_main);
        mInflater = LayoutInflater.from(this);
        initData();
        initView();

    }

    private void initData() {
        mImgIds = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04,
                R.drawable.item05, R.drawable.item06, R.drawable.item07, R.drawable.item08, R.drawable.item09,
                R.drawable.item10, R.drawable.item11, R.drawable.item12};
    }

    private void initView() {
        mGallery = (LinearLayout) findViewById(R.id.id_gallery);

        for (int i = 0; i < mImgIds.length; i++) {

            View view = mInflater.inflate(R.layout.activity_index_gallery_item,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);
            img.setImageResource(mImgIds[i]);
            TextView txt = (TextView) view
                    .findViewById(R.id.id_index_gallery_item_text);
            txt.setText("some info ");
            mGallery.addView(view);
        }
    }
}
