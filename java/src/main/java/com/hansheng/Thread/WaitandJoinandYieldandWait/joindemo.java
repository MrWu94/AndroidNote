package com.hansheng.Thread.WaitandJoinandYieldandWait;

/**
 * Created by hansheng on 16-9-5.
 * 阻塞当前调用函数时所在的线程，直到接收线程执行完毕之后在继续执行，。
 */

public class joindemo {



    public static void main(String... args){

        Worker worker1=new Worker("work-1");
        Worker worker2=new Worker("work-2");
        worker1.start();
        System.out.println("启动线程1");
        try {
            worker1.join();
            System.out.println("启动线程2");
            worker2.start();
            worker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程继续执行");
    }




    static class Worker extends Thread{

        public Worker(String name){
            super(name);
        }
        @Override
        public void run() {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("work in"+getName());
        }
    }
}
