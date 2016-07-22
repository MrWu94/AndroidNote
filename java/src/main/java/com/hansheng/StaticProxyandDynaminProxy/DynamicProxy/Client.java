package com.hansheng.StaticProxyandDynaminProxy.DynamicProxy;

import com.hansheng.StaticProxyandDynaminProxy.StaticProxy.ILawsuit;
import com.hansheng.StaticProxyandDynaminProxy.StaticProxy.XiaoMin;

import java.lang.reflect.Proxy;

/**
 * Created by hansheng on 2016/7/22.
 */
public class Client {

    public static void main(String... args){
        ILawsuit xiaomi=new XiaoMin();

        DynamicProxy proxy=new DynamicProxy(xiaomi);

       ClassLoader loader= xiaomi.getClass().getClassLoader();

        ILawsuit lawer= (ILawsuit) Proxy.newProxyInstance(loader,new Class[]{ILawsuit.class},proxy);

        lawer.submit();
        lawer.burden();
        lawer.defend();
        lawer.finish();
    }
}
