package com.hansheng.studynote.Listview.MutileItem;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.List;

/**
 * Created by hansheng on 16-12-14.
 */

public class ViewHolderAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public ViewHolderAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    /**
     * @Title: getViewTypeCount
     * @Description:两种样式
     * @return: 样式的数目
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * @param position：item位置
     * @Title: getItemViewType
     * @Description:每个item的样式
     * @return: 样式
     */
    @Override
    public int getItemViewType(int position) {
        // 为了方便起见，我们根据奇偶项来区别样式，注意从0开始数
        if (position % 2 != 0) {
            // 奇数项返回0
            return 0;
        } else {
            // 偶数项返回0
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
//        if (convertView == null) {
//            convertView = View.inflate(context, R.layout.listview_m, null);
//        }
//        view = convertView;

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_main);

          // 如果是奇数项
            if (getItemViewType(position) == 0) {
                viewHolder.textView.setText(list.get(position));
                viewHolder.textView.setTextColor(Color.BLUE);
            }
            // 如果是偶数项
            else if (getItemViewType(position) == 1) {
                System.out.println("=====" + viewHolder);
                System.out.println("---" + viewHolder.textView);
                viewHolder.textView.setText(list.get(position));
                viewHolder.textView.setTextColor(Color.GRAY);
            }
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }
}
