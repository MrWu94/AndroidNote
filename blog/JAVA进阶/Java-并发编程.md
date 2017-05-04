**并发（concurrency）** 并发的关注点在于任务切分。举例来说，你是一个创业公司的CEO，开始只有你一个人，你一人分饰多角，一会做产品规划，一会写代码，一会见客户，虽然你不能见客户的同时写代码，但由于你切分了任务，分配了时间片，表现出来好像是多个任务一起在执行。
**并行（parallelism）** 并行的关注点在于同时执行。还是上面的例子，你发现你自己太忙了，时间分配不过来，于是请了工程师，产品经理，市场总监，各司一职，这时候多个任务可以同时执行了
##Runnable与Thread的区别
使用Runnable的好处
 1、可以避免由于Java的单继承特性而带来的局限；
 2、增强程序的健壮性，代码能够被多个线程共享，代码与数据是独立的；
 3、适合多个相同程序代码的线程区处理同一资源的情况。
```java
class MyThread implements Runnable {
    private int ticket = 5;

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                System.out.println("ticket = " + ticket--);
            }
        }
    }
}

class RunnableDemo {
    public static void main(String[] args) {
        MyThread my = new MyThread();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();
    }
}
```

##Volatile
 
  在多线程，同步变量。 线程为了提高效率，将某成员变量(如A)拷贝了一份（如B），线程中对A的访问其实访问的是B。只在某些动作时才进行A和B的同步。因此存在A和B不一致的情况。volatile就是用来避免这种情况的。volatile告诉jvm， 它所修饰的变量不保留拷贝，直接访问主内存中的（也就是上面说的A) 假如pleaseStop没有被声明为volatile，线程执行run的时候检查的是自己的副本， 就不能及时得知其他线程已经调用tellMeToStop()修改了pleaseStop的值。** 简单的说就是synchronized的代码块是确保可见性和原子性的, volatile只能确保可见性, 当且仅当下面条件全部满足时, 才能使用volatile 对变量的写入操作不依赖于变量的当前值, **(++i/i++这种肯定不行), 或者能确保只有单个线程在更新该变量不会与其他状态变量一起纳入不变性条件中访问变量时不需要加锁volatile关键字不起作用，也就是说如下的表达式都不是原子操作n  =  n  +   1 ;n ++ ;**所以在使用volatile关键时一定要谨慎，如果自己没有把握，可以使用synchronized来代替volatile。**
```java
访问变量时不需要加锁volatile关键字不起作用，使用n  =  n  +   1的情况，若线程安全结果是100，而现在结果是小于100
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
加入synchronized的代码块是确保可见性和原子性的，现在结果是正确的
 class  JoinThreadText  extends  Thread
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

```
##Wait、notify、notifyAll
 在调用wait(), notify()或notifyAll()的时候，必须先获得锁，且状态变量须由该锁保护，而固有锁对象与固有条件队列对象又是同一个对象。也就是说，要在某个对象上执行wait，notify，先必须锁定该对象，而对应的状态变量也是由该对象锁保护的。
##AtomicInteger
AtomicInteger，一个提供原子操作的Integer的类。在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，不可避免的会用到synchronized关键字。而AtomicInteger则通过一种线程安全的加减操作接口。来看AtomicInteger提供的接口。
//获取当前的值  
public final int get()
//取当前的值，并设置新的值  
 public final int getAndSet(int newValue)
 //获取当前的值，并自增
 public final int getAndIncrement()
//获取当前的值，并自减
 public final int getAndDecrement()
 //获取当前的值，并加上预期的值
 public final int getAndAdd(int delta)
```
时间比较
public class AtomicIntegerCompareTest {
    private int value;

    public AtomicIntegerCompareTest(int value) {
        this.value = value;
    }

    public synchronized int increase() {
        return value++;
    }

    public static void main(String args[]) {
        long start = System.currentTimeMillis();

        AtomicIntegerCompareTest test = new AtomicIntegerCompareTest(0);
        for (int i = 0; i < 1000000; i++) {
            test.increase();
        }
        long end = System.currentTimeMillis();
        System.out.println("time elapse:" + (end - start));

        long start1 = System.currentTimeMillis();

        AtomicInteger atomic = new AtomicInteger(0);

        for (int i = 0; i < 1000000; i++) {
            atomic.incrementAndGet();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("time elapse:" + (end1 - start1));
    }
}

```
##BlockingQueue阻塞队列
作为BlockingQueue的使用者，我们再也不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给你一手包办了。offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳, 
基于链表的阻塞队列，同ArrayListBlockingQueue类似，其内部也维持着一个数据缓冲队列（该队列由一个链表构成），当生产者往队列中放入一个数据时，队列会从生产者手中获取数据，并缓存在队列内部，而生产者立即返回；只有当队列缓冲区达到最大值缓存容量时 （LinkedBlockingQueue可以通过构造函数指定该值）， 才会阻塞生产者队列，直到消费者从队列中消费掉一份数据，生产者线程会被唤醒，反之对于消费者这端的处理也基于同样的原理。
```
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20);
        for (int i = 0; i < 30; i++) {
            //将指定元素添加到此队列中
            bqueue.put("加入元素" + i);
            System.out.println("向阻塞队列中添加了元素:" + i);
        }
        System.out.println("程序到此运行结束，即将退出----");
    }
}
从执行结果中可以看出，由于队列中元素的数量限制在了20个，因此添加20个元素后，其他元素便在队列外阻塞等待，程序并没有终止。


class BlockingQueueTest1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20);
        for (int i = 0; i < 30; i++) {
            //将指定元素添加到此队列中
            bqueue.put("" + i);
            System.out.println("向阻塞队列中添加了元素:" + i);
            if (i > 18) {
                //从队列中获取队头元素，并将其移出队列
                System.out.println("从阻塞队列中移除元素：" + bqueue.take());
            }
        }
        System.out.println("程序到此运行结束，即将退出----");
    }
}
从结果中可以看出，当添加了第20个元素后，我们从队首移出一个元素，这样便可以继续向队列中添加元素， 之后每添加一个元素，便从将队首元素移除，这样程序便可以执行结束。
```
#FutureTask
FutureTask可以返回执行完毕的数据，并且FutureTask的get方法支持阻塞这两个特性， 我们可以用来预先加载一些可能用到资源，然后要用的时候，调用get方法获取（如果资源加载完，直接返回；否则继续等待其加载完成）。
 FutureTask则是一个RunnableFuture<V>，即实现了Runnbale又实现了Futrue<V>这两个接口，  另外它还可以包装Runnable(实际上会转换为Callable)和Callable ，所以一般来讲是一个符合体了，它可以通过Thread包装来直接执行，也可以提交给ExecuteService来执行  ，并且还可以通过v get()返回执行结果，在线程体没有执行完成的时候，主线程一直阻塞等待，执行完则直接返回结果。 
  其中Runnable实现的是void run()方法，无返回值；Callable实现的是 call()方法，并且可以返回执行结果。其中Runnable可以提交给Thread来包装下 ，直接启动一个线程来执行，而Callable则一般都是提交给ExecuteService来执行。
```
public class RunnableFutureTask {

    /**
     * ExecutorService
     */
    static ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        runnableDemo();
        futureDemo();
    }

    /**
     * runnable, 无返回值
     */
    static void runnableDemo() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("runnable demo : " + fibc(20));
            }
        }).start();
    }

  
    static void futureDemo() {
        try {
            /**
             * 提交runnable则没有返回值, future没有数据
             */
            Future<?> result = mExecutor.submit(new Runnable() {

                @Override
                public void run() {
                    fibc(20);
                }
            });

            System.out.println("future result from runnable : " + result.get());

            /**
             * 提交Callable, 有返回值, future中能够获取返回值
             */
            Future<Integer> result2 = mExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return fibc(20);
                }
            });

            System.out
                    .println("future result from callable : " + result2.get());
            FutureTask<Integer> futureTask = new FutureTask<Integer>(
                    new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            return fibc(20);
                        }
                    });
            // 提交futureTask
            mExecutor.submit(futureTask) ;
            System.out.println("future result from futureTask : "
                    + futureTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 效率底下的斐波那契数列, 耗时的操作
     *
     * @param num
     * @return
     */
    static int fibc(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fibc(num - 1) + fibc(num - 2);
    }
}
```
##Execute、ExecuteService
 Executor接口中之定义了一个方法execute（Runnable command），该方法接收一个Runable实例，它用来执行一个任务，即一个实现了Runnable接口的类。ExecutorService接口继承自Executor接口，它提供了更丰富的实现多线程的方法，比如，ExecutorService提供了关闭自己的方法，以及可为跟踪一个或多个异步任务执行状况而生成 Future 的方法。 可 以调用ExecutorService的shutdown（）方法来平滑地关闭 ExecutorService，调用该方法后，将导致ExecutorService 停止接受任何新的任务且等待已经提交的任务执行完成(已经提交的任务会分两类：一类是已经在执行的，另一类是还没有开始执行的)， 当所有已经提交的任务执行完毕后将会关闭ExecutorService。因此我们一般用该接口来实现和管理多线程。
```
public class ExecutorDemo {
    private static final int MAX = 10;

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//      fixedThredPool(3);
        newCachedThreadPool();
    }

    private static void fixedThredPool(int size) {

        ExecutorService executorService = Executors.newFixedThreadPool(size);

        for (int i = 0; i < MAX; i++) {
            Future<Integer> task = executorService.submit(new Callable<Integer>() {

                public Integer call() throws Exception {
                    // TODO Auto-generated method stub
                    System.out.println("执行线程：" + Thread.currentThread().getName());

                    return fibc(20);
                }
            });
            try {
                System.out.println("第" + i + "次计算结果" + task.get());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private static void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < MAX; i++) {
            executorService.submit(new Runnable() {

                public void run() {
                    // TODO Auto-generated method stub
                    System.out.println("执行线程：" + Thread.currentThread().getName() + ",结果" + fibc(20));
                }
            });
        }
    }

    private static int fibc(int num) {
        // TODO Auto-generated method stub
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fibc(num - 1) + fibc(num - 2);
    }

}
```
##Wait与Sleep的区别
**sleep()睡眠时，保持对象锁，仍然占有该锁；而wait()睡眠时，释放对象锁。** 但是wait()和sleep()都可以通过interrupt()方法打断线程的暂停状态， 从而使线程立刻抛出InterruptedException（但不建议使用该方法）。
```
public class ThreadTest implements Runnable {
    int number = 10;

    public void firstMethod() throws Exception {
        synchronized (this) {
            number += 100;
            System.out.println(number);
        }
    }

    public void secondMethod() throws Exception {
        synchronized (this) {
            /**
             * (休息2S,阻塞线程)
             * 以验证当前线程对象的机锁被占用时,
             * 是否被可以访问其他同步代码块
             */
//            Thread.sleep(2000);
            this.wait(2000);
            number *= 200;
        }
    }

    @Override
    public void run() {
        try {
            firstMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.start();
        threadTest.secondMethod();
    }
}
```
##ReadWiteLock
下面要介绍的是读写锁(ReadWriteLock)， 我们会有一种需求，在对数据进行读写的时候，为了保证数据的一致性和完整性， 需要读和写是互斥的，写和写是互斥的，但是读和读是不需要互斥的，这样读和读不互斥性能更高些
```
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Data data = new Data();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }).start();
        }
    }
}

class Data {
    private int data;// 共享数据
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void set(int data) {
        rwl.writeLock().lock();// 取到写锁
        try {
            System.out.println(Thread.currentThread().getName() + "准备写入数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);
        } finally {
            rwl.writeLock().unlock();// 释放写锁
        }
    }

    public void get() {
        rwl.readLock().lock();// 取到读锁
        try {
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);
        } finally {
            rwl.readLock().unlock();// 释放读锁
        }
    }
}
```
## ReentrantLock 
可重入锁最大的作用是避免死锁
 ReentrantLock 类实现了 Lock ，它拥有与 synchronized 相同的并发性和内存语义，但是添加了类似锁投票、定时锁等候和可中断锁等候的一些特性
```
public class ReentrantloackTest implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    private void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();

    }

    @Override
    public void run() {
        get();
    }

    public static void main(String... args) {
        ReentrantloackTest reentrantlock = new ReentrantloackTest();
        new Thread(reentrantlock).start();
        new Thread(reentrantlock).start();
        new Thread(reentrantlock).start();
        new Thread(reentrantlock).start();
    }
}

```
##synchronized
** synchronized(this)以及非static的synchronized方法（至于stati csynchronized方法请往下看）， 只能防止多个线程同时执行同一个对象的同步代码段。**
**用synchronized(Sync.class)实现了全局锁的效果。**synchronized锁住的是代码还是对象。答案是：synchronized锁住的是括号里的对象，而不是代码。对于非static的synchronized方法，锁的就是对象本身也就是this。最后说说static synchronized方法，static方法可以直接类名加方法名调用，方法中无法使用this，所以它锁的不是this，*而是类的Class对象，所以，static synchronized方法也相当于全局锁，相当于锁住了代码段。
 锁作为并发共享数据，*保证一致性的工具，在JAVA平台有多种实现(如 synchronized 和 ReentrantLock等等是广义上的可重入锁， 而不是单指JAVA下的ReentrantLock。*可重入锁，也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。* 在JAVA环境下 ReentrantLock 和synchronized 都是 可重入锁
```
class Sync {

    public void test() {
        synchronized (Sync.class) {
            System.out.println("test开始..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test结束..");
        }
    }
}

class MyThread extends Thread {

    public void run() {
        Sync sync = new Sync();
        sync.test();
    }
}

class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new MyThread();
            thread.start();
        }
    }
}
```

##Callable
当将一个Callable的对象传递给ExecutorService的submit方法，则该call方法自动在一个线程上执行，并且会返回执行结果Future对象。同样，将Runnable的对象传递给ExecutorService的submit方法， 则该run方法自动在一个线程上执行，并且会返回执行结果Future对象，但是在该Future对象上调用get方法，将返回null。
```
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<Future<String>>();

        //创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult(i));
            //将任务执行结果存储到List中
            resultList.add(future);
        }

        //遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
//                while (!fs.isDone) ;//Future返回如果没有完成，则一直循环等待，直到Future返回完成
                System.out.println(fs.get());     //打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务
                executorService.shutdown();
            }
        }
    }
}


class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，
     * 则该方法自动在一个线程上执行
     */
    public String call() throws Exception {
        System.out.println("call()方法被自动调用！！！    " + Thread.currentThread().getName());
        //该返回结果将被Future的get方法得到
        return "call()方法被自动调用，任务返回的结果是：" + id + "    " + Thread.currentThread().getName();
    }
}

```
##CyclicBarrier
CyclicBarrier（又叫障碍器）同样是Java 5中加入的新特性，使用时需要导入java.util.concurrent.CylicBarrier。它适用于这样一种情况：你希望创建一组任务，它们并发地执行工作，另外的一个任务在这一组任务并发执行结束前一直阻塞等待， 直到该组任务全部执行结束，这个任务才得以执行。这非常像CountDownLatch，只是CountDownLatch是只触发一次的事件，而CyclicBarrier可以多次重用。
```
public class CyclicBarrierTest {
    public static void main(String[] args) {
        //创建CyclicBarrier对象，
        //并设置执行完一组5个线程的并发任务后，再执行MainTask任务
        CyclicBarrier cb = new CyclicBarrier(5, new MainTask());
        new SubTask("A", cb).start();
        new SubTask("B", cb).start();
        new SubTask("C", cb).start();
        new SubTask("D", cb).start();
        new SubTask("E", cb).start();
    }
}

/**
 * 最后执行的任务
 */
class MainTask implements Runnable {
    public void run() {
        System.out.println("......终于要执行最后的任务了......");
    }
}

/**
 * 一组并发任务
 */
class SubTask extends Thread {
    private String name;
    private CyclicBarrier cb;

    SubTask(String name, CyclicBarrier cb) {
        this.name = name;
        this.cb = cb;
    }

    public void run() {
        System.out.println("[并发任务" + name + "]  开始执行");
        for (int i = 0; i < 999999; i++) ;    //模拟耗时的任务
        System.out.println("[并发任务" + name + "]  开始执行完毕，通知障碍器");
        try {
            //每执行完一项任务就通知障碍器
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
```
##Smeaphore
Java并发包中的信号量Semaphore实际上是一个功能完毕的计数信号量，从概念上讲，它维护了一个许可集合， 对控制一定资源的消费与回收有着很重要的意义。Semaphore可以控制某个资源被同时访问的任务数， 它通过acquire（）获取一个许可，release（）释放一个许可。如果被同时访问的任务数已满，则其他acquire的任务进入等待状态，直到有一个任务被release掉，它才能得到许可。 可以看出，Semaphore允许并发访问的任务数一直为5，当然，这里还很容易看出一点， 就是Semaphore仅仅是对资源的并发访问的任务数进行监控，而不会保证线程安全，因此，在访问的时候，要自己控制线程的安全访问。
```
public class SemaphoreTest {
    public static void main(String[] args) {
        //采用新特性来启动和管理线程——内部使用线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //只允许5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        //模拟10个客户端访问
        for (int index = 0; index < 10; index++) {
            final int num = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        //获取许可
                        semp.acquire();
                        System.out.println("线程" +
                                Thread.currentThread().getName() + "获得许可：" + num);
                        //模拟耗时的任务
                        for (int i = 0; i < 999999; i++) ;
                        //释放许可
                        semp.release();
                        System.out.println("线程" +
                                Thread.currentThread().getName() + "释放许可：" + num);
                        System.out.println("当前允许进入的任务个数：" +
                                semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        //关闭线程池
        exec.shutdown();
    }
}
```
