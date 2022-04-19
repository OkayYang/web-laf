package com.ruoyi.wx.util.bean.echartBean;

import com.ruoyi.wx.util.bean.echartBean.Graph;

import java.util.List;

public class Statistic {
    private int information;
    private int success;
    private int number;
    private int flow;
    private List<Graph> column_chart;
    private List<Graph> fan_chart;


    public int getInformation() {
        return information;
    }

    public void setInformation(int information) {
        this.information = information;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public List<Graph> getColumn_chart() {
        return column_chart;
    }

    public void setColumn_chart(List<Graph> column_chart) {
        this.column_chart = column_chart;
    }

    public List<Graph> getFan_chart() {
        return fan_chart;
    }

    public void setFan_chart(List<Graph> fan_chart) {
        this.fan_chart = fan_chart;
    }
}
