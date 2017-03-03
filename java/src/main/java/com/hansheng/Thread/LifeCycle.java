package com.hansheng.Thread;

/**
 * Created by hansheng on 2016/10/3.
 * join()：等待该线程终止。
 * ，在上面的代码中使用了join方法，这个方法的主要功能是保证线程的run方法完成后程序才继续运行
 *  一但线程开始执行run方法，就会一直到这个run方法执行完成这个线程才退出。但在线程执行的过程中
 *  可以通过两个方法使线程暂时停止执行。这两个方法是yield和sleep。thread.yield()在多线程程序中,
 *  为了防止某线程独占CPU资源(这样其它的线程就得不到"响应"了).可以让当前执行的线程"休息"一下.
 *  但是这种thread.yield() 调用,并不保证下一个运行的线程就一定不是该线程.而使用sleep使线程休眠后，
 *  只能在设定的时间后使线程处于就绪状态（在线程休眠结束后，线程不一定会马上执行，只是进入了就绪状态，
 *  等待着系统进行调度）。在使用sleep时要注意，不能在一个线程中来休眠另一个线程。如main方法中使用
 *  thread.sleep(2000)方法是无法使thread线程休眠2秒的，而只能使主线程休眠2秒。在使用sleep方法
 *  时有四点需要注意：
 *   sleep()可使优先级低的线程得到执行的机会，当然也可以让同优先级和高优先级的线程有执行的机会；
 *   yield()只能使同优先级的线程有执行的机会。
 */
public class LifeCycle extends Thread
{
    public void run()
    {
        int n = 0;
        while ((++n) < 1000);
    }

    public static void main(String[] args) throws Exception
    {
        LifeCycle thread1 = new LifeCycle();
        System.out.println("isAlive: " + thread1.isAlive());
        thread1.start();
        System.out.println("isAlive: " + thread1.isAlive());
        thread1.join();  // 等线程thread1结束后再继续执行
        System.out.println("thread1已经结束!");
        System.out.println("isAlive: " + thread1.isAlive());
    }
}