package com.gershire.gb.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gershire.gb.weather.R;
import com.gershire.gb.weather.global.Constants;
import com.gershire.gb.weather.global.WeatherService;


public class WeatherFragment extends Fragment {

    private String city;

    public WeatherFragment() {}

    public static WeatherFragment newInstance(String city) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(Constants.BUNDLE_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = getArguments().getString(Constants.BUNDLE_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        String temp = WeatherService.getWeather(city).getTemperature();
        ((TextView) view.findViewById(R.id.cityTextView)).setText(city);
        ((TextView) view.findViewById(R.id.tempTextView)).setText(temp);
        return view;
    }

    public String getCity() {
        return city;
    }
}
