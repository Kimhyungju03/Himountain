package com.cookandroid.himountain;

import java.util.List;

public class WeatherResponse {

    public List<Daily> daily;

    public static class Daily {
        public Temp temp;
        public List<Weather> weather;
    }

    public static class Temp {
        public double day;
    }

    public static class Weather {
        public String main;
    }
}