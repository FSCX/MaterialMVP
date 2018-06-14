package com.fsc.newsnets.bean;

import java.io.Serializable;

/**
 * 天气实体类
 */
public class WeatherBean implements Serializable {
    private static final long serialVersionUID = 1L;

    //temperature（温度）
    private String temperature;

    //weather（天气）
    private String weather;

    //wind(风)
    private String wind;

    //星期（week)
    private String week;

    //date(日期)
    private String date;

    //图片ziyuan
    private int imageRes;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
