package com.hansheng.thread.threadlocal;

/**
 * Created by hansheng on 2016/7/29.* ImportNew
 * ThreadLocal，直译为“线程本地”或“本地线程”，如果你真的这么认为，那就错了！其实，
 * 它就是一个容器，用于存放线程的局部变量，我认为应该叫做 ThreadLocalVariable（线程局部变量）
 */
public interface Sequence {
    int getNumber();
}
