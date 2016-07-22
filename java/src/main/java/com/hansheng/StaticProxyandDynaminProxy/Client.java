package com.hansheng.StaticProxyandDynaminProxy;

/**
 * Created by hansheng on 2016/7/22.
 */
public class Client {

    public static void main(String... args){
        RealSubject realSubject=new RealSubject();
        ProxySubject proxySubject=new ProxySubject(realSubject);

        proxySubject.visit();
    }
}
