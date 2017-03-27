package com.hansheng.mylibrary.decorator.Observer.weatherobservable;

import com.hansheng.mylibrary.decorator.Observer.weatherobservable.observer.CurrentConditionsDisplay;
import com.hansheng.mylibrary.decorator.Observer.weatherobservable.observer.ForecastDisplay;
import com.hansheng.mylibrary.decorator.Observer.weatherobservable.observer.HeatIndexDisplay;
import com.hansheng.mylibrary.decorator.Observer.weatherobservable.observer.StatisticsDisplay;
import com.hansheng.mylibrary.decorator.Observer.weatherobservable.subject.WeatherData;

public class WeatherStationHeatIndex {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
