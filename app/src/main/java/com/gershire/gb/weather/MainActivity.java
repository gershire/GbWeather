package com.gershire.gb.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String LIFECYCLE = "LIFECYCLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LIFECYCLE, "onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);
        showToast("Activity created");
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
