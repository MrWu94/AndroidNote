/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.hansheng.studynote.viewpager.pageradapterrefresh.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.List;

/**
 * Description:
 * <br/>Program Name:
 * <br/>Date: 2016年3月7日
 *

 */

public class DemoPagerAdapter extends APagerAdapter<String> {

    public DemoPagerAdapter(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position) {
        View view = mInflater.inflate(R.layout.item_vp_demopageradapter, null);
        ImageView imgIV = (ImageView) view.findViewById(R.id.iv_img);
        TextView pageNumTV = (TextView) view.findViewById(R.id.tv_pagenum);
        if (mDataList != null && mDataList.size() > position && mDataList.get(position) != null) {
            pageNumTV.setText("DIY-PageNum-" + mDataList.get(position));
        } else {
            pageNumTV.setText("DIY-PageNum-" + position);
        }
        mViewSparseArray.put(position, view);
        return view;
    }

}
