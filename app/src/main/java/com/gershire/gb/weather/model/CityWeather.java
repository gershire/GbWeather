package com.gershire.gb.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CityWeather implements Parcelable {
    private String name;
    private int temperature;

    public CityWeather(String name, int temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    private CityWeather(Parcel in) {
        name = in.readString();
        temperature = in.readInt();
    }

    public String getName() {
        return name;
    }

    public String getTemperature() {
        String template = "%s%dºC";
        String sign = (temperature > 0) ? "+" : "";
        return String.format(template, sign, temperature);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(temperature);
    }

    public static final Parcelable.Creator<CityWeather> CREATOR =
            new Parcelable.Creator<CityWeather>() {

                @Override
                public CityWeather createFromParcel(Parcel source) {
                    return new CityWeather(source);
                }

                @Override
                public CityWeather[] newArray(int size) {
                    return new CityWeather[size];
                }
            };
}
