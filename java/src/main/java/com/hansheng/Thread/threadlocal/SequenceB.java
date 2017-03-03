package com.hansheng.Thread.ThreadLocal;

/**
 * Created by hansheng on 2016/7/29.
 * 通过 ThreadLocal 封装了一个 Integer 类型的 numberContainer 静态成员变量，
 * 并且初始值是0。再看 getNumber() 方法，首先从 numberContainer 中 get 出当前的值，加1，
 * 随后 set 到 numberContainer 中，最后将 numberContainer 中 get 出当前的值并返回。
 * <p/>
 * 搞清楚 ThreadLocal 的原理之后，有必要总结一下 ThreadLocal 的 API，其实很简单。
 * <p/>
 * public void set(T value)：将值放入线程局部变量中
 * public T get()：从线程局部变量中获取值
 * public void remove()：从线程局部变量中移除值（有助于 JVM 垃圾回收）
 * protected T initialValue()：返回线程局部变量中的初始值（默认为 null）
 *
 */
public class SequenceB implements Sequence {

    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceB();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
