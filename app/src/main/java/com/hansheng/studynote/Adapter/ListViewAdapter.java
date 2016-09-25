package com.hansheng.studynote.Adapter;

import android.content.Context;

import com.hansheng.studynote.Adapter.abslistview.EasyLVAdapter;
import com.hansheng.studynote.Adapter.abslistview.EasyLVHolder;
import com.hansheng.studynote.R;

import java.util.List;

/**
 * Created by hansheng on 2016/9/24.
 */

public class ListViewAdapter extends EasyLVAdapter<Bean> {

    public ListViewAdapter(Context context, List<Bean> list, int... layoutIds) {
        super(context, list, layoutIds);
    }

    @Override
    public void convert(EasyLVHolder holder, int position, final Bean bean) {
        holder.setText(R.id.tv, bean.name);
    }

    @Override
    public int getLayoutIndex(int position, Bean item) {
        if (position % 2 == 0)
            return 1;
        else return 0;
    }
}