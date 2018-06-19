package com.fsc.newsnets.weather.widget;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fsc.newsnets.R;
import com.fsc.newsnets.bean.WeatherBean;
import com.fsc.newsnets.weather.presenter.WeatherPresenter;
import com.fsc.newsnets.weather.presenter.WeatherPresenterImpl;
import com.fsc.newsnets.weather.view.WeatherView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WeatherFragment extends Fragment implements WeatherView {
    @BindView(R.id.city)
    TextView mCity;
    @BindView(R.id.today)
    TextView mToday;
    @BindView(R.id.weatherImage)
    ImageView mWeatherImage;
    @BindView(R.id.weatherTemp)
    TextView mWeatherTemp;
    @BindView(R.id.wind)
    TextView mWind;
    @BindView(R.id.weather)
    TextView mWeather;
    @BindView(R.id.weather_content)
    LinearLayout mWeatherContent;
    @BindView(R.id.weather_layout)
    LinearLayout mWeatherLayout;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.root_layout)
    FrameLayout mRootLayout;
    Unbinder unbinder;
    private WeatherPresenter mWeatherPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherPresenter = new WeatherPresenterImpl(this,getActivity().getApplication());
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, null);

        unbinder = ButterKnife.bind(this, view);
        mWeatherPresenter.loadWeatherData();
        return view;
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showWeatherLayout() {
        mWeatherLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCity(String city) {
        mCity.setText(city);
    }

    @Override
    public void setToday(String data) {
        mToday.setText(data);
    }

    @Override
    public void setTemperature(String temperature) {
        mWeatherTemp.setText(temperature);
    }

    @Override
    public void setWind(String wind) {
        mWind.setText(wind);
    }

    @Override
    public void setWeather(String weather) {
        mWeather.setText(weather);
    }

    @Override
    public void setWeatherImage(int res) {
        mWeatherImage.setImageResource(res);
    }

    @Override
    public void setWeatherData(List<WeatherBean> list) {
        List<View> adapterList = new ArrayList<>();
        for (WeatherBean weatherBean : list) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_weather, null, false);
            TextView date = (TextView) view.findViewById(R.id.date);
            ImageView todayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
            TextView todayTemperature = (TextView) view.findViewById(R.id.weatherTemp);
            TextView todayWind = (TextView) view.findViewById(R.id.wind);
            TextView todayWeather = (TextView) view.findViewById(R.id.weather);

            date.setText(weatherBean.getWeek());
            todayWeatherImage.setImageResource(weatherBean.getImageRes());
            todayTemperature.setText(weatherBean.getTemperature());
            todayWind.setText(weatherBean.getWind());
            todayWeather.setText(weatherBean.getWeather());
            mWeatherContent.addView(view);
            adapterList.add(view);
        }
    }

    @Override
    public void showErrorToast(String msg) {
        Snackbar.make(getActivity().findViewById(R.id.drawer_layout), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
