package com.cookandroid.himountain;

public class Mountain {

    private String name;
    private String height;
    private String location;
    private String difficulty;
    private int imageResId;
    private String rating;

    public Mountain(String name,
                    String height,
                    String location,
                    String difficulty,
                    String rating,
                    int imageResId) {

        this.name = name;
        this.height = height;
        this.location = location;
        this.difficulty = difficulty;
        this.rating = rating;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getLocation() {
        return location;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getRating() {
        return rating;
    }
}