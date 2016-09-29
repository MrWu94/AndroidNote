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

public class Demo3Adapter extends RecyclerView.Adapter<Demo3Adapter.VH> {
    private final Context mContext;
    private ArrayList<Data> mTitles;

    public Demo3Adapter(Context context) {
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
        holder.mTextView.setText("点position为2的item是删除");
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public void add(int index) {
        Data data = new Data();
        mTitles.add(index, data);
        notifyItemInserted(index);
    }

    public void remove(int position) {
        mTitles.remove(position);
        notifyItemRemoved(position);
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView mTextView;
        Demo3Adapter mAdapter;

        VH(View view, Demo3Adapter adapter) {
            super(view);
            mAdapter = adapter;
            mTextView = (TextView) view.findViewById(R.id.text_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getLayoutPosition() == 2) {
                        mAdapter.remove(getLayoutPosition());
                    } else {
                        mAdapter.add(getLayoutPosition());
                    }
                }
            });
        }
    }
}

