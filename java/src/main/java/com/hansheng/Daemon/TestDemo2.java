package com.hansheng.Daemon;

/**
 * Created by hansheng on 2016/9/27.
 * 运行结果：文件daemon.txt中没有"daemon"字符串。
 * <p>
 * 但是如果把thread.setDaemon(true); //设置守护线程注释掉，文件daemon.txt是可以被写入daemon字符串的
 */

public class TestDemo2 {
    public static void main(String[] args) throws InterruptedException

    {

        Runnable tr = new TestRunnable();

        Thread thread = new Thread(tr);

        thread.setDaemon(true); //设置守护线程

        thread.start(); //开始执行分进程

    }
}
