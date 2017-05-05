package com.hansheng.studynote.listview.RecyclerView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.List;

/**
 * Created by hansheng on 16-12-12.
 */

public class MutileItemAdapter extends LoadAdapter {


    public MutileItemAdapter(Context mContext, List<News> list) {
        super(mContext, list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LoadAdapter.ViewHolder viewHolder = null;
        switch (VIEWTYPE.values()[viewType]) {
            case NORMAL:
                viewHolder = new TextViewHolder(mLayoutInflater.inflate(R.layout.list_item, parent, false));
                System.out.println("normal");
                break;
            case INRMAL:
                viewHolder = new ImageViewHolder(mLayoutInflater.inflate(R.layout.list_item, parent, false));
                System.out.println("INnormal");
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            System.out.println("onview"+list.get(position).getTitle()+list.get(position).getImageUrl().get(position));
            ((TextViewHolder) holder).mTextView.setText(list.get(position).getTitle());
        } else if (holder instanceof ImageViewHolder) {
            System.out.println("size"+list.get(position).getTitle());
            ((ImageViewHolder) holder).mTextView.setText(list.get(position).getTitle());
        }
    }


    public class TextViewHolder extends ViewHolder {

        TextView mTextView;

        TextViewHolder(View view) {
            super(view);
            mTextView= (TextView) view.findViewById(R.id.tv_main);

        }
    }

    public class ImageViewHolder extends ViewHolder {

        TextView mTextView;

        TextView mImageView;

        ImageViewHolder(View view) {
            super(view);
            mTextView= (TextView) view.findViewById(R.id.tv_main);
            mImageView= (TextView) view.findViewById(R.id.imageUrl);
        }
    }
}
