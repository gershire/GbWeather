package com.gershire.gb.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String BUNDLE_CITY = "CITY";
    private static final String LIFECYCLE = "LIFECYCLE";
    private static final String INPUT = "INPUT";

    private TextView tempOutput = null;
    private AutoCompleteTextView cityInput = null;
    private String city = "";
    private TextView.OnEditorActionListener cityInputListener =
            new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEARCH && tempOutput != null) {
                        city = v.getText().toString();
                        Log.d(INPUT, "onEditorAction: input city is " + city);
                        updateTemp();
                        handled = true;
                    }
                    return handled;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LIFECYCLE, "onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showToast("Activity created");
    }

    private void initView() {
        tempOutput = findViewById(R.id.tempLabel);
        cityInput = findViewById(R.id.cityInput);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                WeatherService.getCities());
        cityInput.setAdapter(adapter);
        cityInput.setOnEditorActionListener(cityInputListener);
    }


    private void populateView() {
        if (city != null) {
            cityInput.setText(city);
            updateTemp();
        }
    }

    private void updateTemp() {
        tempOutput.setText(WeatherService.getTemp(city));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(LIFECYCLE, "onSaveInstanceState: called");
        outState.putString(BUNDLE_CITY, city);
        super.onSaveInstanceState(outState);
        showToast("Activity state saved");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d(LIFECYCLE, "onRestoreInstanceState: called");
        super.onRestoreInstanceState(savedInstanceState);
        city = savedInstanceState.getString(BUNDLE_CITY);
        populateView();
        showToast("Activity state restored");
    }

    @Override
    protected void onStart() {
        Log.d(LIFECYCLE, "onStart: called");
        super.onStart();
        showToast("Activity started");
    }

    @Override
    protected void onResume() {
        Log.d(LIFECYCLE, "onResume: called");
        super.onResume();
        showToast("Activity resumed");
    }

    @Override
    protected void onRestart() {
        Log.d(LIFECYCLE, "onRestart: called");
        super.onRestart();
        showToast("Activity restarted");
    }

    @Override
    protected void onPause() {
        Log.d(LIFECYCLE, "onPause: called");
        super.onPause();
        showToast("Activity paused");
    }

    @Override
    protected void onStop() {
        Log.d(LIFECYCLE, "onStop: called");
        super.onStop();
        showToast("Activity stopped");
    }

    @Override
    protected void onDestroy() {
        Log.d(LIFECYCLE, "onDestroy: called");
        super.onDestroy();
        showToast("Activity destroyed");
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
