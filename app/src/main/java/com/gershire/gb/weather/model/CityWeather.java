package com.gershire.gb.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gershire.gb.weather.R;

public class CityWeather implements Parcelable {
    private String name;
    private int temperature;
    private int bgId;
    private WeatherConditions conditions;

    public CityWeather(String name, int temperature, int bgId, WeatherConditions conditions) {
        this.name = name;
        this.temperature = temperature;
        this.bgId = bgId;
        this.conditions = conditions;
    }

    private CityWeather(Parcel in) {
        name = in.readString();
        temperature = in.readInt();
        bgId = in.readInt();
        conditions = WeatherConditions.valueOf(in.readString());
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

    public int getConditions() {
        switch (conditions) {
            case SUN:
                return R.drawable.sunny;
            case SNOW:
                return R.drawable.snow_occasional;
            case SLEET:
                return R.drawable.sleet;
            case OVERCAST:
                return R.drawable.overcast;
        }
        return R.drawable.sunny;
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
        dest.writeString(conditions.toString());
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
