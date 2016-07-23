package com.hansheng.MVC;


/**
 * Created by hansheng on 2016/7/23.
 */
public interface QuackObservable {
    void registerObserver(Observer observer);
    void notifyObservers();
}
