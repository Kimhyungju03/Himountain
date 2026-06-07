package com.cookandroid.himountain;

import java.util.List;

public class ForecastResponse {

    public List<ForecastItem> list;

    public static class ForecastItem {
        public Main main;
    }

    public static class Main {
        public float temp;
    }
}