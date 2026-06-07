package com.cookandroid.himountain;

import java.util.List;

public class WeatherResponse {

    public Main main;
    public List<Weather> weather;
    public Wind wind;

    public static class Main {
        public float temp;
        public float feels_like;
        public int humidity;
    }

    public static class Weather {
        public String main;
        public String description;
    }

    public static class Wind {
        public float speed;
    }
}