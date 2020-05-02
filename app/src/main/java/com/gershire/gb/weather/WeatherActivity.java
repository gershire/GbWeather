package com.gershire.gb.weather;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.gershire.gb.weather.fragment.WeatherFragment;

public class WeatherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_weather);

        if (savedInstanceState == null) {
            WeatherFragment fragment = new WeatherFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.weather_fragment, fragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}
