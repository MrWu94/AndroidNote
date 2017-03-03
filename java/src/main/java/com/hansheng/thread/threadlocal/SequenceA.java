package com.hansheng.Thread.ThreadLocal;

/**
 * Created by hansheng on 2016/7/29.
 * 由于线程启动顺序是随机的，所以并不是0、1、2这样的顺序，
 * 这个好理解。为什么当 Thread-0 输出了1、2、3之后，而
 * Thread-2 却输出了4、5、6呢？线程之间竟然共享了 static 变量！
 * 这就是所谓的“非线程安全”问题了。
 */
public class SequenceA implements Sequence {
    private static int number = 0;

    public int getNumber() {
        number = number + 1;
        return number;
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceA();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
