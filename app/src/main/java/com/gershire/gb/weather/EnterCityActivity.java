package com.gershire.gb.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import static com.gershire.gb.weather.global.Constants.INTENT_CITY;

public class EnterCityActivity extends AppCompatActivity {

    private String city = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_city);

        final AutoCompleteTextView cityInput = findViewById(R.id.cityInput);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                WeatherService.getCities());
        cityInput.setAdapter(adapter);

        Button showBtn = findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityInput.getText().toString();
                if (city.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.not_chosen_alert), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(INTENT_CITY, city);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
