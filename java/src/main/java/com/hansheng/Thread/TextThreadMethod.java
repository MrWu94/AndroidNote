package com.hansheng.Thread;

/**
 * Created by hansheng on 2016/10/3.
 * 调用yield()，不同优先级的线程永远不会得到执行机会。
 * sleep()使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会执行；
 * yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。
 * yield()：暂停当前正在执行的线程对象，并执行其他线程。
 */
class TestThreadMethod extends Thread{
    public static int shareVar = 0;
    public TestThreadMethod(String name){
        super(name);
    }
    public void run(){
        for(int i=0; i<4; i++){
            System.out.print(Thread.currentThread().getName());
            System.out.println(" : " + i);
          Thread.yield();
//          /* （2） */
//            try{
//                Thread.sleep(3000);
//            }
//            catch(InterruptedException e){
//                System.out.println("Interrupted");
//            }}
            }}
}
 class TestThread{
    public static void main(String[] args){
        TestThreadMethod t1 = new TestThreadMethod("t1");
        TestThreadMethod t2 = new TestThreadMethod("t2");
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }
}