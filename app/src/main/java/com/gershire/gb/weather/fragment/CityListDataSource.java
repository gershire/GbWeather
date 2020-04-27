package com.gershire.gb.weather.fragment;

import android.content.res.Resources;

import com.gershire.gb.weather.R;

public class CityListDataSource implements DataSource<String> {
    private String[] cities;

    public CityListDataSource(Resources resources) {
        cities = resources.getStringArray(R.array.cities);
    }

    @Override
    public String getItem(int i) {
        return cities[i];
    }

    @Override
    public int getSize() {
        return cities.length;
    }
}
