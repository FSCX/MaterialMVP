package com.fsc.newsnets.main.view;

/**
 * main接口
 * 是开关的接口，实现可选news,images,weather,about之一
 */
public interface MainView {
    void switchNews();
    void switchImages();
    void switchWeather();
    void switchAbout();
}
