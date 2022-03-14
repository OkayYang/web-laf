package com.ruoyi.wx.util.baidu.domain;

import com.google.gson.annotations.SerializedName;

public class BaiduOcrSfzResult {

    @SerializedName("words_result")
    private WordsResult wordsResult;
    @SerializedName("photo_location")
    private Location photoLocation;

    @SerializedName("direction")
    private int direction;

    //normal正常
    @SerializedName("image_status")
    private String imageStatus;

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public WordsResult getWordsResult() {
        return wordsResult;
    }

    public void setWordsResult(WordsResult wordsResult) {
        this.wordsResult = wordsResult;
    }

    public Location getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(Location photoLocation) {
        this.photoLocation = photoLocation;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "BaiduOcrSfzResult{" +
                "wordsResult=" + wordsResult +
                ", photoLocation=" + photoLocation +
                ", direction=" + direction +
                ", imageStatus='" + imageStatus + '\'' +
                '}';
    }
}
