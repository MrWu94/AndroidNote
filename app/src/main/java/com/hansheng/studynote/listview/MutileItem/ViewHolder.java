package com.hansheng.studynote.listview.MutileItem;

import android.view.View;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-14.
 */
public class ViewHolder {
    TextView itemText;

    // 构造函数中就初始化View
    public ViewHolder(View convertView) {
        itemText = (TextView) convertView.findViewById(R.id.tv_item);
    }

    // 得到一个ViewHolder
    public static ViewHolder getViewHolder(View convertView) {
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        return viewHolder;
    }
}


