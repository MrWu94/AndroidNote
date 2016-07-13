package com.hansheng.studynote.eventbus.event;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hansheng.studynote.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by hansheng on 2016/7/13.
 */
public class ItemDetailFragment extends Fragment {

    private TextView tvDetail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item,
                container, false);
        tvDetail = (TextView) rootView.findViewById(R.id.item_detail);
        return rootView;
    }

    @Subscribe
    public void onEvent(Item item){
        if(item!=null){
            tvDetail.setText(item.content);
        }
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
