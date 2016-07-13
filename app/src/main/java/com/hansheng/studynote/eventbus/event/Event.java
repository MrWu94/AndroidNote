package com.hansheng.studynote.eventbus.event;

import java.util.List;

/**
 * Created by hansheng on 2016/7/13.
 */
public class Event {
    public static class ItemListEvent{
        private List<Item> items;
        public ItemListEvent(List<Item> items){
            this.items=items;
        }

        public List<Item> getItems(){
            return items;
        }

    }
}
