package com.hansheng.studynote.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;
import com.hansheng.studynote.recyclerview.data.Data;
import com.hansheng.studynote.recyclerview.data.DataUtils;

import java.util.ArrayList;

/**
 * Created by hansheng on 16-9-29.
 */
public class Demo2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private ArrayList<Data> mTitles;

    public Demo2Adapter(Context context) {
        ArrayList<Data> datas = DataUtils.getDatas();
        if (datas == null) {
            datas = new ArrayList<>();
        }
        mTitles = datas;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        return position % 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;

        switch (viewType) {
            case 0:
                vh = new VH(View.inflate(mContext, R.layout.item_text2, null));
                break;
            case 1:
                vh = new TextViewHolder(View.inflate(mContext, R.layout.item_text3, null));
                break;
            case 2:
            default:
                vh = new ImageViewHolder(View.inflate(mContext, R.layout.item_image, null));
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VH) {
            ((VH) holder).mTextView.setText(mTitles.get(position).getNum());
        } else if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).mTextView.setText(mTitles.get(position).getNum());
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mImageView.setImageResource(mTitles.get(position).getResImage());
        }
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView mTextView;

        VH(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text_view);
        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        TextViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTextView.setText("点击了");
                }
            });
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        ImageViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.image_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "点击图片", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}