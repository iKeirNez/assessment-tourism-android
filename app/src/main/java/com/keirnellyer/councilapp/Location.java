package com.keirnellyer.councilapp;

public class Location {
    private String name;
    private String description;
    private int imageResId;

    public Location(String name, String description, int imageResId) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
