package com.hansheng.studynote.eventbus.event;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by hansheng on 2016/7/13.
 */
public class ItemListFragment extends ListFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);
                    EventBus.getDefault().post(new Event.ItemListEvent(Item.ITEMS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        EventBus.getDefault().post(getListView().getItemAtPosition(position));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventThread(Event.ItemListEvent event){
        setListAdapter(new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_1,android.R.id.text1,event.getItems()));

    }
}
