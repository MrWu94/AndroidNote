package com.hansheng.StaticProxyandDynaminProxy.StaticProxy;

/**
 * Created by hansheng on 2016/7/22.
 */
public class Lawyer implements ILawsuit {

    public Lawyer(ILawsuit lawsuit) {
        this.lawsuit = lawsuit;
    }

    private ILawsuit lawsuit;
    @Override
    public void submit() {
        lawsuit.submit();
    }

    @Override
    public void burden() {
        lawsuit.burden();
    }

    @Override
    public void defend() {
        lawsuit.defend();
    }

    @Override
    public void finish() {
        lawsuit.finish();
    }
}
