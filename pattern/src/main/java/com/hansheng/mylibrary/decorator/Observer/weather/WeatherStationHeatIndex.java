package com.hansheng.mylibrary.decorator.Observer.weather;

import com.hansheng.mylibrary.decorator.Observer.weather.impl.CurrentConditionsDisplayObserver;
import com.hansheng.mylibrary.decorator.Observer.weather.impl.ForecastDisplayObserver;
import com.hansheng.mylibrary.decorator.Observer.weather.impl.HeatIndexDisplayObserver;
import com.hansheng.mylibrary.decorator.Observer.weather.impl.StatisticsDisplayObserver;
import com.hansheng.mylibrary.decorator.Observer.weather.subject.WeatherData;

public class WeatherStationHeatIndex {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionsDisplayObserver currentDisplay = new CurrentConditionsDisplayObserver(weatherData);
		StatisticsDisplayObserver statisticsDisplay = new StatisticsDisplayObserver(weatherData);
		ForecastDisplayObserver forecastDisplay = new ForecastDisplayObserver(weatherData);
		HeatIndexDisplayObserver heatIndexDisplay = new HeatIndexDisplayObserver(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
