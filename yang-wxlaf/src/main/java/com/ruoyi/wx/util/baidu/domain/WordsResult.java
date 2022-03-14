package com.ruoyi.wx.util.baidu.domain;


import com.google.gson.annotations.SerializedName;
import com.ruoyi.wx.util.baidu.domain.Unit;

public class WordsResult {

    @SerializedName("姓名")
    private Unit name;
    @SerializedName("民族")
    private Unit nation;
    @SerializedName("住址")
    private Unit address;
    @SerializedName("公民身份号码")
    private Unit number;
    @SerializedName("出生")
    private Unit birth;
    @SerializedName("性别")
    private Unit sex;

    public Unit getName() {
        return name;
    }

    public void setName(Unit name) {
        this.name = name;
    }

    public Unit getNation() {
        return nation;
    }

    public void setNation(Unit nation) {
        this.nation = nation;
    }

    public Unit getAddress() {
        return address;
    }

    public void setAddress(Unit address) {
        this.address = address;
    }

    public Unit getNumber() {
        return number;
    }

    public void setNumber(Unit number) {
        this.number = number;
    }

    public Unit getBirth() {
        return birth;
    }

    public void setBirth(Unit birth) {
        this.birth = birth;
    }

    public Unit getSex() {
        return sex;
    }

    public void setSex(Unit sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "WordsResult{" +
                "name=" + name +
                ", nation=" + nation +
                ", address=" + address +
                ", number=" + number +
                ", birth=" + birth +
                ", sex=" + sex +
                '}';
    }
}
