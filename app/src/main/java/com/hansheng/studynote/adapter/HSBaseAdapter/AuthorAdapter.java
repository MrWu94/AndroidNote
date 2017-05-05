package com.hansheng.studynote.adapter.HSBaseAdapter;

import android.widget.AbsListView;

import com.hansheng.studynote.R;

import java.util.Collection;

/**
 * Created by hansheng on 16-9-29.
 */

public class AuthorAdapter extends HSBaseAdapter<String> {

    public AuthorAdapter(AbsListView view, Collection<String> mDatas, int itemLayoutId) {
        super(view, mDatas, itemLayoutId);
    }
    @Override
    public void convert(AdapterHolder helper,String item,
                        boolean isScrolling) {
       helper.setText(R.id.id_num,item);
    }
}
