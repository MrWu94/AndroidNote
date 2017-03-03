package com.hansheng.Thread;

/**
 * Created by hansheng on 2016/9/27.
 * 在多线程，同步变量。 线程为了提高效率，将某成员变量(如A)拷贝了一份（如B），
 * 线程中对A的访问其实访问的是B。只在某些动作时才进行A和B的同步。因此存在A和B不一致的情况
 * 。volatile就是用来避免这种情况的。
 * volatile告诉jvm， 它所修饰的变量不保留拷贝，直接访问主内存中的（也就是上面说的A)
 * 假如pleaseStop没有被声明为volatile，线程执行run的时候检查的是自己的副本，
 * 就不能及时得知其他线程已经调用tellMeToStop()修改了pleaseStop的值。
 * <p>
 * 简单的说就是synchronized的代码块是确保可见性和原子性的, volatile只能确保可见性
 * 当且仅当下面条件全部满足时, 才能使用volatile
 * 对变量的写入操作不依赖于变量的当前值, (++i/i++这种肯定不行), 或者能确保只有单个线程在更新
 * 该变量不会与其他状态变量一起纳入不变性条件中
 * 访问变量时不需要加锁
 * volatile关键字不起作用，也就是说如下的表达式都不是原子操作：
 * <p>
 * n  =  n  +   1 ;
 * n ++ ;
 *
 * ，如n=n+1、n++ 等，volatile关键字将失效，只有当变量的值和自身上一个值无关时对该变量的操作才是原子级别的，
 * 如n = m + 1，这个就是原级别的。
 * 所以在使用volatile关键时一定要谨慎，如果自己没有把握，可以使用synchronized来代替volatile。
 */

public class VolatileText extends Thread {
    private volatile boolean pleaseStop;


    public void run() {

        while (!pleaseStop) {

            // do some stuff...

        }

    }


    public void tellMeToStop() {

        pleaseStop = true;

    }
}

class  JoinThread  extends  Thread
{
    public   static volatile int  n  =   0 ;
    public   void  run()
    {
        for  ( int  i  =   0 ; i  <   10 ; i ++ )
            try
            {
                n  =  n  +   1 ;
                sleep( 3 );  //  为了使运行结果更随机，延迟3毫秒

            }
            catch  (Exception e)
            {
            }
    }

    public   static   void  main(String[] args)  throws  Exception
    {

        Thread threads[]  =   new  Thread[ 100 ];
        for  ( int  i  =   0 ; i  <  threads.length; i ++ )
            //  建立100个线程
            threads[i]  =   new  JoinThread();
        for  ( int  i  =   0 ; i  <  threads.length; i ++ )
            //  运行刚才建立的100个线程
            threads[i].start();
        for  ( int  i  =   0 ; i  <  threads.length; i ++ )
            //  100个线程都执行完后继续
            threads[i].join();
        System.out.println( " n= "   +  JoinThread.n);
    }
}

  class  JoinThread1  extends  Thread
{
    public   static int  n  =   0 ;

    public static   synchronized   void  inc()
    {
        n ++ ;
    }
    public   void  run()
    {
        for  ( int  i  =   0 ; i  <   10 ; i ++ )
            try
            {
                inc();  //  n = n + 1 改成了 inc();
                sleep( 3 );  //  为了使运行结果更随机，延迟3毫秒

            }
            catch  (Exception e)
            {
            }
    }

    public   static   void  main(String[] args)  throws  Exception
    {

        Thread threads[]  =   new  Thread[ 100 ];
        for  ( int  i  =   0 ; i  <  threads.length; i ++ )
            //  建立100个线程
            threads[i]  =   new  JoinThread();
        for  ( int  i  =   0 ; i  <  threads.length; i ++ )
            //  运行刚才建立的100个线程
            threads[i].start();
        for  ( int  i  =   0 ; i  <  threads.length; i ++ )
            //  100个线程都执行完后继续
            threads[i].join();
        System.out.println( " n= "   +  JoinThread.n);
    }
}
