package com.hansheng.studynote.eventbus.event;

/**
 * Created by hansheng on 2016/7/6.
 */
public class MessageEvent {
    public final String message;

    public MessageEvent(String message) {
        this.message = message;
    }
}