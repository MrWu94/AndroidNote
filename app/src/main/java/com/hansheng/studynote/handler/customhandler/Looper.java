package com.hansheng.studynote.handler.customhandler;


/**
 * Created by mrwu on 2017/6/23.
 */

public class Looper {

    private static ThreadLocal<Looper> mLooper = new ThreadLocal<>();
    private static Looper mainLooper;
    private MessageQueue queue = new MessageQueue();

    public static void prepare(boolean isMain) {
        if (mLooper.get() != null) {
            throw new RuntimeException("looper has prepared");
        }
        mLooper.set(new Looper());
        if (isMain) {
            mainLooper = mLooper.get();
        }
    }

    /**
     * 获取主线程的Looper
     *
     * @return
     */
    public static Looper mainLooper() {
        return mainLooper;
    }

    public static void loop() {
        // 获取当前线程的Looper
        Looper looper = mLooper.get();
        if (looper == null) {
            throw new RuntimeException("must call looper.perpared()");
        }
        while (true) {
            // 从当前线程的MessageQueue中获取需要处理的消息
            // 如果消息为空的话就等待
            Message message = looper.queue.next();
            if (message.target != null) {
                message.target.handleMessage(message);
            } else if (message.callback != null) {
                message.callback.run();
            }
        }
    }

    public MessageQueue getQueue() {
        return queue;
    }
}
