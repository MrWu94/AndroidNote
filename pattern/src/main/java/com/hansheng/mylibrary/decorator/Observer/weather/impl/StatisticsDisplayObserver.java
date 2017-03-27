package com.hansheng.mylibrary.decorator.Observer.weather.impl;

import com.hansheng.mylibrary.decorator.Observer.weather.observer.Observer;
import com.hansheng.mylibrary.decorator.Observer.weather.subject.Subject;

public class StatisticsDisplayObserver implements Observer, DisplayElement {
	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum= 0.0f;
	private int numReadings;
	private Subject weatherData;

	public StatisticsDisplayObserver(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	public void update(float temp, float humidity, float pressure) {
		tempSum += temp;
		numReadings++;

		if (temp > maxTemp) {
			maxTemp = temp;
		}
 
		if (temp < minTemp) {
			minTemp = temp;
		}

		display();
	}

	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
			+ "/" + maxTemp + "/" + minTemp);
	}
}
