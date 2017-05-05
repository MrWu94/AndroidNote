package com.hansheng.studynote.gallery;

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
        mImgIds = new int[]{R.drawable.ic_item01, R.drawable.ic_item02, R.drawable.ic_item03, R.drawable.ic_item04,
                R.drawable.ic_item05, R.drawable.ic_item06, R.drawable.ic_item07, R.drawable.ic_item08, R.drawable.ic_item09,
                R.drawable.ic_item10, R.drawable.ic_item11, R.drawable.ic_item12};
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
