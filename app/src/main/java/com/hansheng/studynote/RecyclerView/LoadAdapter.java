package com.hansheng.studynote.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hansheng on 16-12-12.
 */

public abstract class LoadAdapter  extends RecyclerView.Adapter<LoadAdapter.ViewHolder> {

    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected List<News> list;

    public LoadAdapter(Context mContext, List<News> list) {
        this.mContext = mContext;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    enum VIEWTYPE {
        NORMAL(0), INRMAL(1);
        private int viewType;

        VIEWTYPE(int viewType) {
            this.viewType = viewType;
        }

        public int getViewType() {
            return viewType;
        }
    }

    protected LayoutInflater layoutInflater;


    @Override
    public LoadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(LoadAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        int itemType;
        if (list.get(position).getImageUrl().size() == 0) {
            itemType = VIEWTYPE.NORMAL.getViewType();
        } else {
            itemType = VIEWTYPE.INRMAL.getViewType();

        }
        return itemType;
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
