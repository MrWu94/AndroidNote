package com.hansheng.Daemon;

/**
 * Created by hansheng on 2016/9/27.
 * 运行结果：文件daemon.txt中没有"daemon"字符串。
 * <p>
 * 但是如果把thread.setDaemon(true); //设置守护线程注释掉，文件daemon.txt是可以被写入daemon字符串的
 * <p>
 * 在Java中有两类线程：User Thread(用户线程)、Daemon Thread(守护线程)
 * 用个比较通俗的比如，任何一个守护线程都是整个JVM中所有非守护线程的保姆：
 * 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
 * Daemon的作用是为其他线程的运行提供便利服务，守护线程最典型的应用就是 GC (垃圾回收器)，它就是一个很称职的守护者。
 * User和Daemon两者几乎没有区别，唯一的不同之处就在于虚拟机的离开：如果 User Thread已经全部退出运行了，只剩下Daemon Thread存在了，
 * 虚拟机也就退出了。 因为没有了被守护者，Daemon也就没有工作可做了，也就没有继续运行程序的必要了。
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
