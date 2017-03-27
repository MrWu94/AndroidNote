package com.hansheng.mylibrary.decorator.Observer.weather.subject;

import com.hansheng.mylibrary.decorator.Observer.weather.observer.Observer;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
