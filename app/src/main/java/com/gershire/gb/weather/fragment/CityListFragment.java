package com.gershire.gb.weather.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gershire.gb.weather.R;
import com.gershire.gb.weather.WeatherActivity;
import com.gershire.gb.weather.global.Constants;
import com.gershire.gb.weather.global.WeatherService;
import com.gershire.gb.weather.model.CityWeather;

import static android.app.Activity.RESULT_OK;


public class CityListFragment extends Fragment {

    private CityWeather selectedCity = null;
    private boolean isLandscape;
    private final static int SHOW_WEATHER_REQUEST_CODE = 2;

    public CityListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        LinearLayout layout = (LinearLayout) view;

        RecyclerView recycler = layout.findViewById(R.id.recycler_view);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(layout.getContext()));

        CityListAdapter adapter = new CityListAdapter(new CityListDataSource(getResources()));
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new CityListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String cityName = ((TextView) view).getText().toString();
                selectedCity = WeatherService.getWeather(cityName);
                Log.d(Constants.TAG_INPUT, "onClick: " + cityName);
                showWeather();
            }
        });

        if (bundle != null)
            selectedCity = bundle.getParcelable(Constants.BUNDLE_CITY);
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        showWeather();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Constants.BUNDLE_CITY, selectedCity);
        super.onSaveInstanceState(outState);
    }

    private void addElement(LinearLayout layout, String city) {
        TextView element = new TextView(getContext());
        element.setText(city);
        element.setTextSize(30);
        layout.addView(element);
        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = ((TextView) v).getText().toString();
                selectedCity = WeatherService.getWeather(cityName);
                Log.d(Constants.TAG_INPUT, "onClick: " + cityName);
                showWeather();
            }
        });
    }

    private void showWeather() {
        if (isLandscape) {
            showWeatherFragment();
        } else if (selectedCity != null) {
            showWeatherActivity();
        }
    }

    private void showWeatherFragment() {
        WeatherFragment fragment = null;
        if (getFragmentManager() != null) {
            fragment = (WeatherFragment) getFragmentManager()
                    .findFragmentById(R.id.weather_frame);
        }
        if (fragment == null || selectedCity == null || !selectedCity.equals(fragment.getCityWeather())) {
            WeatherFragment f = WeatherFragment.newInstance(selectedCity);

            getFragmentManager().beginTransaction()
                    .replace(R.id.weather_frame, f)
                    .commit();
        }
    }

    private void showWeatherActivity() {
        Intent intent = new Intent();
        Context ctx = getActivity();
        if (ctx == null)
            Log.e(Constants.TAG_ERROR, "Could not get activity");
        else {
            intent.setClass(getActivity(), WeatherActivity.class);
            intent.putExtra(Constants.BUNDLE_CITY, selectedCity);
            startActivityForResult(intent, SHOW_WEATHER_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != SHOW_WEATHER_REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            selectedCity = null;
        }
    }
}
