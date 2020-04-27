package com.gershire.gb.weather.fragment;

public interface DataSource<T> {
    T getItem(int i);
    int getSize();
}
