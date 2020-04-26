package com.gershire.gb.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gershire.gb.weather.global.Constants;
import com.gershire.gb.weather.model.CityWeather;

public class MainActivity extends AppCompatActivity {

    private TextView tempOutput = null;
    private TextView cityNameOutput = null;
    private CityWeather cityWeather = null;
    private final static int ENTER_CITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        findViewById(R.id.enterCityBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnterCityActivity.class);
                startActivityForResult(intent, ENTER_CITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != ENTER_CITY_REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK && data != null) {
            cityWeather = data.getParcelableExtra(Constants.INTENT_CITY);
            populateView();
        }
    }

    private void initView() {
        tempOutput = findViewById(R.id.tempLabel);
        cityNameOutput = findViewById(R.id.cityName);
    }


    private void populateView() {
        if (cityWeather != null) {
            cityNameOutput.setText(cityWeather.getName());
            tempOutput.setText(cityWeather.getTemperature());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Constants.BUNDLE_CITY, cityWeather);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cityWeather = savedInstanceState.getParcelable(Constants.BUNDLE_CITY);
        populateView();
    }
}
