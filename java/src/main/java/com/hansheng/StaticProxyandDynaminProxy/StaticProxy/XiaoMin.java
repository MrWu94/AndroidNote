package com.hansheng.StaticProxyandDynaminProxy.StaticProxy;

/**
 * Created by hansheng on 2016/7/22.
 */
public class XiaoMin implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("boss 欠他的工资");
    }

    @Override
    public void burden() {
        System.out.println("这是合同书");
    }

    @Override
    public void defend() {
        System.out.println("证据充分");
    }

    @Override
    public void finish() {
        System.out.println("成功，发工资");
    }
}
