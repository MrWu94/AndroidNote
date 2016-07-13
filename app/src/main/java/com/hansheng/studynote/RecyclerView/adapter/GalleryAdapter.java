package com.hansheng.studynote.RecyclerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.List;

/**
 * Created by hansheng on 2016/7/13.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    private LayoutInflater mInflater;

    private List<Integer> mDatas;

    public GalleryAdapter(Context context, List<Integer> mDatas) {
        mInflater= LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mInflater.inflate(R.layout.recycler_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.mImg= (ImageView) view.findViewById(R.id.id_index_gallery_item_image);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final GalleryAdapter.ViewHolder holder, final int position) {
        holder.mImg.setImageResource(mDatas.get(position));
        if(onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        ImageView mImg;
        TextView mTxt;
    }
}
