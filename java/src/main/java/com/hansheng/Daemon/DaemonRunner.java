package com.hansheng.Daemon;

import java.util.Scanner;

/**
 * Created by hansheng on 17-3-1.
 */

public class DaemonRunner implements Runnable {
    public void run() {
        while (true) {
            for (int i = 1; i <= 3; i++) {

                System.out.println("守护线程:"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread daemonThread = new Thread(new DaemonRunner());
        // 设置为守护进程
        daemonThread.setDaemon(true);
        daemonThread.start();
        System.out.println("isDaemon = " + daemonThread.isDaemon());
        Scanner scanner = new Scanner(System.in);
        // 接受输入，使程序在此停顿，一旦接受到用户输入,main线程结束，JVM退出!
        scanner.next();

        //AddShutdownHook方法增加JVM停止时要做处理事件：

        //当JVM退出时，打印JVM Exit语句.
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("JVM Exit!");
            }
        });
    }
}

