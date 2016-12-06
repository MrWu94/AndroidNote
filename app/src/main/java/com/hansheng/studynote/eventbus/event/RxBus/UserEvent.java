package com.hansheng.studynote.eventbus.event.RxBus;

/**
 * Created by hansheng on 16-12-6.
 */
public class UserEvent {
    long id;
    String name;
    public UserEvent(long id,String name) {
        this.id= id;
        this.name= name;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}