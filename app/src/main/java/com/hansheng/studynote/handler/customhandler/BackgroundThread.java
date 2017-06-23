package com.hansheng.studynote.handler.customhandler;

/**
 * Created by mrwu on 2017/6/23.
 */

public class BackgroundThread extends Thread {
    Handler uiHandler;

    public BackgroundThread(Handler handler, String name) {
        uiHandler = handler;
        setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":后台处理进度" + i);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            final int finaI = i;
            uiHandler.postMessage(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":前台更新UI-进度" + finaI);
                }
            });
        }
        uiHandler.postMessage(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":下载完成");
            }
        });
    }
}