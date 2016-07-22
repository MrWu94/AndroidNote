package com.hansheng.StaticProxyandDynaminProxy;

/**
 * Created by hansheng on 2016/7/22.
 */
public class RealSubject extends Subject {
    @Override
    public void visit() {
        System.out.println("real subject");
    }
}
