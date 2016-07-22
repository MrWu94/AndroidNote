package com.hansheng.StaticProxyandDynaminProxy;

/**
 * Created by hansheng on 2016/7/22.
 */
public class ProxySubject extends Subject{

    private RealSubject subject;

    public ProxySubject(RealSubject subject) {
        this.subject = subject;
    }

    @Override
    public void visit() {
        subject.visit();
    }
}
