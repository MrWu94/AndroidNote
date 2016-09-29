package com.hansheng.studynote.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.recyclerview.data.Data;
import com.hansheng.studynote.recyclerview.data.DataUtils;

import java.util.ArrayList;

/**
 * Created by hansheng on 16-9-29.
 */
public class Demo5Adapter extends RecyclerView.Adapter<Demo5Adapter.VH> {

    private final Context mContext;
    private ArrayList<Data> mTitles;

    public Demo5Adapter(Context context) {
        ArrayList<Data> datas = DataUtils.getDatas();
        if (datas == null) {
            datas = new ArrayList<>();
        }
        mTitles = datas;
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(View.inflate(mContext, R.layout.item_text2, null), this);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.mTextView.setText(mTitles.get(position).getNum());
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    /**
     * 返回值设置为int类型,可以做多种类型的支持,若只想支持两种状态,也可以是boolean
     */
    public int isFeatureItem(int position) {
        if (position % 4 == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView mTextView;
        Demo5Adapter mAdapter;

        VH(View view, Demo5Adapter adapter) {
            super(view);
            mAdapter = adapter;
            mTextView = (TextView) view.findViewById(R.id.text_view);
        }
    }
}