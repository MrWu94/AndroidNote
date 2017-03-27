package com.hansheng.mylibrary.decorator.Observer.weather.impl;

import com.hansheng.mylibrary.decorator.Observer.weather.observer.Observer;
import com.hansheng.mylibrary.decorator.Observer.weather.subject.Subject;

public class CurrentConditionsDisplayObserver implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private Subject weatherData;
	
	public CurrentConditionsDisplayObserver(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}
	
	public void display() {
		System.out.println("Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity");
	}
}
