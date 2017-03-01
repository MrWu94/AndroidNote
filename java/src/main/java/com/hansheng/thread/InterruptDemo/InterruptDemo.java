package com.hansheng.thread.InterruptDemo;

/**
 * Created by mrwu on 2017/3/1.
 * <p>
 * <p>
 * 如果对Java中断没有一个全面的了解，可能会误以为被中断的线程将立马退出运行，但事实并非如此。
 * 当MyThread获得CPU执行时，第6行的 if 测试中，检测到中断标识被设置。即MyThread线程检测到了main线程想要中断它的 请求。
 * <p>
 * 大多数情况下，MyThread检测到了中断请求，对该中断的响应是：退出执行（或者说是结束执行）。
 * <p>
 * 但是，上面第5至8行for循环，是执行break语句跳出for循环。但是，线程并没有结束，它只是跳出了for循环而已，
 * 它还会继续执行第12行的代码....
 * <p>
 * 因此，我们的问题是，当收到了中断请求后，如何结束该线程呢？
 * <p>
 * 一种可行的方法是使用 return 语句 而不是 break语句。。。。。哈哈。。。
 * <p>
 * 当然，一种更优雅的方式则是：抛出InterruptedException异常。
 * <p>
 * 当MyThread线程检测到中断标识为true后，在第9行抛出InterruptedException异常。这样，该线程就不能再执行其他的正常语句了
 * (如，第13行语句不会执行)。
 * 这里表明：interrupt()方法有两个作用，一个是将线程的中断状态置位(中断状态由false变成true)；
 * 另一个则是：让被中断的线程抛出InterruptedException异常。
 * <p>
 * 这是很重要的。这样，对于那些阻塞方法(比如 wait() 和 sleep())而言，当另一个线程调用interrupt()中断该线程时，
 * 该线程会从阻塞状态退出并且抛出中断异常。这样，我们就可以捕捉到中断异常，并根据实际情况对该线程从阻塞方法中异常
 * 退出而进行一些处理
 * <p>
 * 这里sort方法是个非常耗时的操作，也就是说主线程休眠一秒钟后调用stop的时候，线程t还在执行sort方法。就是这样一个简单的方法，也会抛出错误！换一句话说，调用stop后，大部分Java字节码都有可能抛出错误，哪怕是简单的加法！
 * <p>
 *
 * 中断可控，stop导致对象的不一致
 * 如果线程当前正持有锁，stop之后则会释放该锁。由于此错误可能出现在很多地方，那么这就让编程人员防不胜防，
 * 极易造成对象状态的不一致。例如，对象obj中存放着一个范围值：最小值low，最大值high，且low不得大于high，
 * 这种关系由锁lock保护，以避免并发时产生竞态条件而导致该关系失效。假设当前low值是5，high值是10，当线程t获取lock后，
 * 将low值更新为了15，此时被stop了，真是糟糕，如果没有捕获住stop导致的Error，low的值就为15，high还是10，
 * 这导致它们之间的小于关系得不到保证，也就是对象状态被破坏了！如果在给low赋值的时候catch住stop导致的Error
 * 则可能使后面high变量的赋值继续，但是谁也不知道Error会在哪条语句抛出，如果对象状态之间的关系更复杂呢？
 * 这种方式几乎是无法维护的，
 * 太复杂了！如果是中断操作，它决计不会在执行low赋值的时候抛出错误，这样程序对于对象状态一致性就是可控的。
 */

public class InterruptDemo {

    public static void main(String[] args) {
        try {
            MyThread1 thread = new MyThread1();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();//请求中断MyThread线程
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.interrupted()) {
                System.out.println("should be stopped and exit");
                break;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("this line is also executed. thread does not stopped");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
    }
}
//因此，一个更好的解决方案是：调用 interrupt() 以 “重新中断” 当前线程。改进MyThread类中catch异常的方式，如下：

//这样，就由 生吞异常 变成了 将 异常事件 进一步扩散了。
//捕捉 InterruptedException 后恢复中断状态

class MyThread1 extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.interrupted()) {
                    System.out.println("should be stopped and exit");
                    throw new InterruptedException();
                }
                System.out.println("i=" + (i + 1));
            }
            System.out.println("this line cannot be executed. cause thread throws exception");
        } catch (InterruptedException e) {
            /**这样处理不好
             * System.out.println("catch interrupted exception");
             * e.printStackTrace();
             */
            Thread.currentThread().interrupt();//这样处理比较好
        }
    }
}
