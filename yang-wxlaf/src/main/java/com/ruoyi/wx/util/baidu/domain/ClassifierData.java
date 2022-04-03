package com.ruoyi.wx.util.baidu.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassifierData {
    @SerializedName("ret")
    List<ClassifierRet> ret;
    @SerializedName("clockwiseAngle")
    private String clockwiseAngle;
    @SerializedName("templateSign")
    private String templateSign;

    public List<ClassifierRet> getRet() {
        return ret;
    }

    public void setRet(List<ClassifierRet> ret) {
        this.ret = ret;
    }

    public String getClockwiseAngle() {
        return clockwiseAngle;
    }

    public void setClockwiseAngle(String clockwiseAngle) {
        this.clockwiseAngle = clockwiseAngle;
    }

    public String getTemplateSign() {
        return templateSign;
    }

    public void setTemplateSign(String templateSign) {
        this.templateSign = templateSign;
    }
}
