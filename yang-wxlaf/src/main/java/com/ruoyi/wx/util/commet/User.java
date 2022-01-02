package com.ruoyi.wx.util.commet;

public class User {
    private String avatar;
    private String nickName;
    private String gender;
    private Long uid;

    public User() {
    }

    public User(String avatar, String nickName, String gender,Long uid) {
        this.avatar = avatar;
        this.nickName = nickName;
        this.gender = gender;
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
