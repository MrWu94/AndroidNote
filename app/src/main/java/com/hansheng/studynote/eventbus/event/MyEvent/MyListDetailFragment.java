package com.hansheng.studynote.eventbus.event.MyEvent;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.eventbus.event.Item;

/**
 * Created by hansheng on 2016/7/13.
 */
public class MyListDetailFragment extends Fragment {
    private TextView tvDetail;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // register
        EventBus.getInstance().register(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Unregister
        EventBus.getInstance().unregister(this);
    }

    /** List点击时会发送些事件，接收到事件后更新详情 */
    public void onEventAsync(Item item)
    {
        if (item != null)
            tvDetail.setText(item.content);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_item,
                container, false);
        tvDetail = (TextView) rootView.findViewById(R.id.item_detail);
        return rootView;
    }
}
