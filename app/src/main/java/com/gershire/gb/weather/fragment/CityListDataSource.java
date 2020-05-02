package com.gershire.gb.weather.fragment;

import android.content.res.Resources;

import com.gershire.gb.weather.R;
import com.gershire.gb.weather.global.WeatherService;
import com.gershire.gb.weather.model.CityWeather;

public class CityListDataSource implements DataSource<CityWeather> {
    private String[] cities;

    public CityListDataSource(Resources resources) {
        cities = resources.getStringArray(R.array.cities);
    }

    @Override
    public CityWeather getItem(int i) {
        return WeatherService.getWeather(cities[i]);
    }

    @Override
    public int getSize() {
        return cities.length;
    }
}
