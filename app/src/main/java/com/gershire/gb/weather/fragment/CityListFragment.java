package com.gershire.gb.weather.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gershire.gb.weather.R;
import com.gershire.gb.weather.global.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityListFragment extends Fragment {

    private String selectedCity = null;
    private boolean isLandscape;

    public CityListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        String[] cities = getResources().getStringArray(R.array.cities);
        LinearLayout layout = (LinearLayout) view;
        for (String city : cities) {
            addElement(layout, city);
        }
        if (bundle != null)
            selectedCity = bundle.getString(Constants.BUNDLE_CITY);
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        showWeather();
    }

    private void addElement(LinearLayout layout, String city) {
        TextView element = new TextView(getContext());
        element.setText(city);
        element.setTextSize(30);
        layout.addView(element);
        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCity = ((TextView) v).getText().toString();
                showWeather();
            }
        });
    }

    private void showWeather() {
        if (isLandscape) {
            // create fragment
        } else {
            // call activity
        }
    }
}
