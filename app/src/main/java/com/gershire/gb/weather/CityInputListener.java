package com.gershire.gb.weather;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class CityInputListener implements TextView.OnEditorActionListener {
    private TextView tempOut = null;

    void setTempOut(TextView tempOut) {
        this.tempOut = tempOut;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEARCH && tempOut != null) {
            WeatherService.lastInput = v.getText().toString();
            String temp = WeatherService.getTemp(WeatherService.lastInput);
            tempOut.setText(temp);
            handled = true;
        }
        return handled;
    }
}
