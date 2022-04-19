package com.ruoyi.wx.util.baidu.bean;




public class Location {
    private int top;
    private int left;
    private int width;
    private int height;

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Location{" +
                "top=" + top +
                ", left=" + left +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
