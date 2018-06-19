package com.fsc.newsnets.weather.model;

import android.content.Context;

/**
 * 是天气的model接口
 */
public interface WeatherModel {
    //加载天气
    void loadWeatherData(String cityName,WeatherModelImpl.LoadWeatherListener listener);
    //加载位置（定位）
    void loadLocation(Context context, WeatherModelImpl.LoadLocationListener listener);
}
