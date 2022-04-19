package com.ruoyi.wx.util.baidu.bean;

import com.google.gson.annotations.SerializedName;

public class ClassifierRet {
    @SerializedName("location")
    private Location location;
    @SerializedName("word_name")
    private String name;
    @SerializedName("word")
    private String word;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
