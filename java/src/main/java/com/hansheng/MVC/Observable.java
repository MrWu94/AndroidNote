package com.hansheng.MVC;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by hansheng on 2016/7/23.
 */
public class Observable implements QuackObservable {
    ArrayList<Observer> observers = new ArrayList<Observer>();
    QuackObservable duck;

    public Observable(QuackObservable duck) {
        this.duck = duck;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }



    public void notifyObservers() {
        Iterator<Observer> iterator = observers.iterator();
        while (iterator.hasNext()) {
            Observer observer = iterator.next();
            observer.update(duck);
        }
    }

    public Iterator<Observer> getObservers() {
        return observers.iterator();
    }
}
