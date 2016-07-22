package com.hansheng.StaticProxyandDynaminProxy.StaticProxy;

/**
 * Created by hansheng on 2016/7/22.
 */
public class Client {
    public static void main(String... args){
        ILawsuit xiaomi=new XiaoMin();
        ILawsuit lawyer=new Lawyer(xiaomi);

        lawyer.burden();
        lawyer.submit();
        lawyer.defend();
        lawyer.finish();
    }
}
