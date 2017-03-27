package com.hansheng.mylibrary.decorator.Observer.weather;

import com.hansheng.mylibrary.decorator.Observer.weather.impl.CurrentConditionsDisplayObserver;
import com.hansheng.mylibrary.decorator.Observer.weather.impl.ForecastDisplayObserver;
import com.hansheng.mylibrary.decorator.Observer.weather.impl.StatisticsDisplayObserver;
import com.hansheng.mylibrary.decorator.Observer.weather.subject.WeatherData;

/**
 * 出版者+订阅者=观察者模式
 * 观察者模式定义了对象之间的一对多依赖，这样一来，当一个对象改变状态时，它的所有依赖者都会收到通知并通知更新。
 * 主题和观察者定义了一对多的关系，观察者依赖于此主题，只要主题状态一有变化，观察者就会被通知。
 * 设计原则：为了交互对象之间的松耦合设计而努力。
 *
 * 当两个对象之间松耦合，他们依然可以交互，但是不太清楚彼此的细节。观察者模式提供了一种对象设计，让主题和观察者之间松耦合
 *
 */

public class WeatherStation {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplayObserver currentDisplay =
                new CurrentConditionsDisplayObserver(weatherData);
        StatisticsDisplayObserver statisticsDisplay = new StatisticsDisplayObserver(weatherData);
        ForecastDisplayObserver forecastDisplay = new ForecastDisplayObserver(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
