package com.hansheng.studynote.eventbus.event.MyEvent;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hansheng.studynote.eventbus.event.Event.ItemListEvent;
import com.hansheng.studynote.eventbus.event.Item;

import static com.hansheng.studynote.eventbus.event.MyEvent.EventBus.getInstance;

/**
 * Created by hansheng on 2016/7/13.
 */
public class MyItemListFragment  extends ListFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Register
        getInstance().register(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Unregister
        getInstance().unregister(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        // 开启线程加载列表
        new Thread()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(2000); // 模拟延时
                    // 发布事件，在后台线程发的事件
                    getInstance().post(new ItemListEvent(Item.ITEMS));
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            };
        }.start();
    }

    public void onEventUI(ItemListEvent event)
    {
        setListAdapter(new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, event.getItems()));
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position,
                                long id)
    {
        super.onListItemClick(listView, view, position, id);
        EventBus.getInstance().post(getListView().getItemAtPosition(position));
    }
}
