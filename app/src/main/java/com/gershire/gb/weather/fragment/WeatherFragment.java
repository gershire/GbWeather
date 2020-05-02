package com.gershire.gb.weather.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.gershire.gb.weather.R;
import com.gershire.gb.weather.global.Constants;
import com.gershire.gb.weather.model.CityWeather;
import com.google.android.material.snackbar.Snackbar;


public class WeatherFragment extends Fragment {

    private CityWeather cityWeather;

    public WeatherFragment() {}

    public static WeatherFragment newInstance(CityWeather cityWeather) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.BUNDLE_CITY, cityWeather);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cityWeather = getArguments().getParcelable(Constants.BUNDLE_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        if (cityWeather != null) {
            ((TextView) view.findViewById(R.id.cityTextView)).setText(cityWeather.getName());
            ((TextView) view.findViewById(R.id.tempTextView)).setText(cityWeather.getTemperature());
            Context context = inflater.getContext();
            ((ImageView) view.findViewById(R.id.cityBackGround))
                    .setImageDrawable(context.getDrawable(cityWeather.getBgId()));
            ((ImageView) view.findViewById(R.id.conditionsIcon))
                    .setImageDrawable(context.getDrawable(cityWeather.getConditions()));
            Snackbar.make(view, getString(R.string.selected_city) + cityWeather.getName(), Snackbar.LENGTH_SHORT).show();
        }
        return view;
    }

    public CityWeather getCityWeather() {
        return cityWeather;
    }
}
