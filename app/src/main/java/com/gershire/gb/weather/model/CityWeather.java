package com.gershire.gb.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CityWeather implements Parcelable {
    private String name;
    private int temperature;
    private int bgId;

    public CityWeather(String name, int temperature, int bgId) {
        this.name = name;
        this.temperature = temperature;
        this.bgId = bgId;
    }

    private CityWeather(Parcel in) {
        name = in.readString();
        temperature = in.readInt();
        bgId = in.readInt();
    }

    public String getName() {
        return name;
    }

    public String getTemperature() {
        String template = "%s%dºC";
        String sign = (temperature > 0) ? "+" : "";
        return String.format(template, sign, temperature);
    }

    public int getBgId() {
        return bgId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(temperature);
        dest.writeInt(bgId);
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
