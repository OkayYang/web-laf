package com.ruoyi.wx.util;

import com.ruoyi.wx.domain.LafRelease;

import java.util.List;

public class LafReleaseInfo {
    private  User publish_user;
    private LafRelease lafRelease;



    public LafRelease getLafRelease() {
        return lafRelease;
    }

    public void setLafRelease(LafRelease lafRelease) {
        this.lafRelease = lafRelease;
    }

    public User getPublish_user() {
        return publish_user;
    }

    public void setPublish_user(User publish_user) {
        this.publish_user = publish_user;
    }

    @Override
    public String toString() {
        return "LafReleaseInfo{" +
                "publish_user=" + publish_user +
                ", lafRelease=" + lafRelease +
                '}';
    }
}
