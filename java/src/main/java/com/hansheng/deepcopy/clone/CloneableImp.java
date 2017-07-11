package com.hansheng.deepcopy.clone;

/**
 * Created by mrwu on 2017/7/11.
 */

public  class CloneableImp implements Cloneable {
    public int count;
    public Child child;


    @Override
    public Object clone() throws CloneNotSupportedException {
        CloneableImp obj = (CloneableImp)super.clone();
        obj.child = (Child) child.clone();
        return obj;
    }
}