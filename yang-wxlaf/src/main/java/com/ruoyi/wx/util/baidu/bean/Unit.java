package com.ruoyi.wx.util.baidu.bean;

import com.google.gson.annotations.SerializedName;

public class Unit {
    @SerializedName("location")
    private Location location;
    @SerializedName("words")
    private String words;

    public Unit() {
    }

    public Unit(Location location, String words) {
        this.location = location;
        this.words = words;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String word) {
        this.words = word;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "location=" + location +
                ", words='" + words + '\'' +
                '}';
    }
}
