package com.gershire.gb.weather.global;

import com.gershire.gb.weather.R;
import com.gershire.gb.weather.model.CityWeather;

import java.util.HashMap;
import java.util.Map;

public class WeatherService {
    private static Map<String, CityWeather> weatherMap = new HashMap<String, CityWeather>() {{
        put("london", new CityWeather("London", 20, R.drawable.london));
        put("moscow", new CityWeather("Moscow", 12, R.drawable.moscow));
        put("norilsk", new CityWeather("Norilsk", 4, R.drawable.norilsk));
        put("kokshetau", new CityWeather("Kokshetau",0, R.drawable.kokshetau));
        put("resolute", new CityWeather("Resolute", -16, R.drawable.resolute));
        put("лондон", new CityWeather("Лондон",20, R.drawable.london));
        put("москва", new CityWeather("Москва", 12, R.drawable.moscow));
        put("норильск", new CityWeather("Норильск", 4, R.drawable.norilsk));
        put("кокшетау", new CityWeather("Кокшетау", 0, R.drawable.kokshetau));
        put("резолют", new CityWeather("Резолют", -16, R.drawable.resolute));
    }};

    public static String[] getCities() {
        String[] c = weatherMap.keySet().toArray(new String[0]);
        for (int i = 0; i < c.length; ++i) {
            c[i] = capitalize(c[i]);
        }
        return c;
    }

    private static String capitalize(String s) {
        if (!s.isEmpty() && Character.isLowerCase(s.charAt(0))) {
            return s.substring(0, 1).toUpperCase() + s.substring(1);
        } else {
            return s;
        }
    }

    public static CityWeather getWeather(String city) {
        if (city != null) {
            return weatherMap.get(city.toLowerCase());
        } else {
            return null;
        }
    }
}
