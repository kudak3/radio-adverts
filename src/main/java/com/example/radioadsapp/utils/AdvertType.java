package com.example.radioadsapp.utils;

public enum AdvertType {
    TWITTER("Twitter"),
    INSTAGRAM("Instagram"),
            FACEBOOK("Facebook"),
    YOUTUBE("Youtube"),
    ONAIR("OnAir");

    private final String name;

    AdvertType(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
