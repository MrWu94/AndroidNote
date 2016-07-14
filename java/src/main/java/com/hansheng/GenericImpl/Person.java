package com.hansheng.GenericImpl;

/**
 * Created by hansheng on 2016/7/14.
 */
public class Person<T extends Info> {
    @Override
    public String toString() {
        return "Person{" +
                "info=" + info +
                '}';
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    private T info;

    public Person(T info) {
        this.info = info;
    }
}
