package com.gershire.gb.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gershire.gb.weather.global.Constants;
import com.gershire.gb.weather.global.WeatherService;

public class MainActivity extends AppCompatActivity {

    private TextView tempOutput = null;
    private TextView cityNameOutput = null;
    private String city = "";
    private final static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        findViewById(R.id.enterCityBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EnterCityActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            city = data != null ? data.getStringExtra(Constants.INTENT_CITY) : null;
            populateView();
        }
    }

    private void initView() {
        tempOutput = findViewById(R.id.tempLabel);
        cityNameOutput = findViewById(R.id.cityName);
    }


    private void populateView() {
        if (city != null) {
            cityNameOutput.setText(city);
            updateTemp();
        }
    }

    private void updateTemp() {
        tempOutput.setText(WeatherService.getTemp(city));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(Constants.BUNDLE_CITY, city);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        city = savedInstanceState.getString(Constants.BUNDLE_CITY);
        populateView();
    }
}
