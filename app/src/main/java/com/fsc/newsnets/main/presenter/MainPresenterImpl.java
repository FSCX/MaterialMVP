package com.fsc.newsnets.main.presenter;

import com.fsc.newsnets.R;
import com.fsc.newsnets.main.view.MainView;

public class MainPresenterImpl  implements MainPresenter {
    private MainView IMainView;

    public MainPresenterImpl(MainView IMainView) {
        this.IMainView = IMainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id){
            case R.id.navigation_item_news:
                IMainView.switchNews();
                break;
            case R.id.navigation_item_images:
                IMainView.switchImages();
                break;
            case R.id.navigation_item_weather:
                IMainView.switchWeather();
                break;
            case R.id.navigation_item_about:
                IMainView.switchAbout();
                break;
            default:
                break;
        }
    }
}
