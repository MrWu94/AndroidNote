package com.hansheng.studynote.handler.customhandler;



/**
 * Created by mrwu on 2017/6/23.
 */

public class Handler {

    Looper looper;

    public Handler(Looper looper) {
        this.looper = looper;
    }

    public Handler() {
        this(Looper.mainLooper());
    }

    public void handleMessage(Message message) {
        if (message.callback != null) {
            message.callback.run();
        }
    };

    public void sendMessage(Message message) {
        looper.getQueue().add(message);
    }

    public void sendMessageEmpty(int what) {
        Message message = new Message();
        message.what = what;
        looper.getQueue().add(message);
    }

    public void postMessage(Runnable callback) {
        Message message = new Message();
        message.callback = callback;
        looper.getQueue().add(message);
    }
}
