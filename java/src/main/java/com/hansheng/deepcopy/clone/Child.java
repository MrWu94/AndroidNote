package com.hansheng.deepcopy.clone;

/**
 * Created by mrwu on 2017/7/11.
 */

public class Child implements  Cloneable{

    public String name;

    public Child(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Child [name=" + name + "]";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
