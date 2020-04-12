package com.gershire.gb.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CityInputListener cityInputListener = new CityInputListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                WeatherService.getCities());

        TextView tempOutput = findViewById(R.id.tempLabel);
        cityInputListener.setTempOut(tempOutput);

        AutoCompleteTextView cityInput = findViewById(R.id.cityInput);
        tempOutput.setText(WeatherService.getLastTemp());
        cityInput.setAdapter(adapter);
        cityInput.setOnEditorActionListener(cityInputListener);
    }
}
