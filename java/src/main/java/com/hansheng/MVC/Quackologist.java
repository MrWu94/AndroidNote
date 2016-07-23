package com.hansheng.MVC;

/**
 * Created by hansheng on 2016/7/23.
 */
public class Quackologist implements Observer {
    @Override
    public void update(QuackObservable duck) {
        System.out.println("quackologist:"+duck+"just quacked");
    }
}
