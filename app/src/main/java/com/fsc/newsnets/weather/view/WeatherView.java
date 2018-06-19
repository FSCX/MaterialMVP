package com.fsc.newsnets.weather.view;

import com.fsc.newsnets.bean.WeatherBean;

import java.util.List;

/**
 * 天气mvp模式的view接口
 */
public interface WeatherView {
    //进度条显示隐藏
    void showProgress();
    void hideProgress();

    //布局
    void showWeatherLayout();

    //天气的数据
    void setCity(String city);
    void setToday(String data);
    void setTemperature(String temperature);
    void setWind(String wind);
    void setWeather(String weather);
    void setWeatherImage(int res);
    void setWeatherData(List<WeatherBean> list);
    void showErrorToast(String msg);
}
