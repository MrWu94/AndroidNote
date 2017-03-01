package com.hansheng.thread.ThreadPause;

import static java.lang.Thread.currentThread;

/**
 * Created by hansheng on 17-2-28.
 */


public class ThreadPauseDemo {
    public static void main(String args[]) throws InterruptedException {
        Game game = new Game();
        Thread t1 = new Thread(game, "T1");
        t1.start(); // 现在停止Game线程
        System.out.println(currentThread().getName() + " is stopping game thread");
        game.stop(); // 查看Game线程停止的状态
        // TimeUnit.MILLISECONDS.sleep(200);
        System.out.println(currentThread().getName() + " is finished now");
    }
}


class Game implements Runnable {
    private volatile boolean isStopped = false;

    public void run() {
        while (!isStopped) {
            System.out.println("Game thread is running......");
            System.out.println("Game thread is now going to pause");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Game thread is now resumed......");
        }
        System.out.println("Game thread is stopped......");
    }

    public void stop() {
        isStopped = true;
    }
}

