package com.gershire.gb.weather;

public class DataStorage {
    private static final Object lock = new Object();
    private static DataStorage INSTANCE = null;
    private String city = "";

    private DataStorage() {}

    public static DataStorage getInstance() {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null)
                    INSTANCE = new DataStorage();
            }
        }
        return INSTANCE;
    }

    public String getCity() {
        String c;
        synchronized (lock) {
            c = city;
        }
        return c;
    }

    public void setCity(String city) {
        synchronized (lock) {
            this.city = city;
        }
    }
}
