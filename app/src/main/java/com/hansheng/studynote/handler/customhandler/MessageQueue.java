package com.hansheng.studynote.handler.customhandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrwu on 2017/6/23.
 */

public class MessageQueue {

    List<Message> messages = new ArrayList<>();

    public synchronized Message next() {
        while (messages.size() == 0) {
            try {
                //消息为空，等待
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        notifyAll();
        return messages.remove(0);
    }

    public synchronized void add(Message message) {
        messages.add(message);
        //唤醒
        notifyAll();
    }
}
