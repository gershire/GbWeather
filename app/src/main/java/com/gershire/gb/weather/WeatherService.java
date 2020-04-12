package com.gershire.gb.weather;

import java.util.HashMap;
import java.util.Map;

public class WeatherService {
    static String lastInput = "";
    private static Map<String, Integer> tempMap = new HashMap<String, Integer>() {{
        put("london", 20);
        put("moscow", 12);
        put("norilsk", 4);
        put("kokshetau", 0);
        put("resolute", -16);
        put("лондон", 20);
        put("москва", 12);
        put("норильск", 4);
        put("кокшетау", 0);
        put("резолют", -16);
    }};

    static String[] getCities() {
        String[] c = tempMap.keySet().toArray(new String[0]);
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

    static String getTemp(String city) {
        Integer temp = tempMap.get(city.toLowerCase());
        if (temp == null)
            return "";
        String template = "%s%dºC";
        String sign = (temp > 0) ? "+" : "";
        return String.format(template, sign, temp);
    }

    static String getLastTemp() {
        return getTemp(lastInput);
    }
}
