package com.hansheng.studynote.handler.customhandler;

/**
 * Created by mrwu on 2017/6/23.
 */

public class App {

    Handler handler;

    public static void main(String[] args) {
        Looper.prepare(true);
        new BackgroundThread(new Handler(), "后台进程").start();

        Looper.loop();
    }

}
