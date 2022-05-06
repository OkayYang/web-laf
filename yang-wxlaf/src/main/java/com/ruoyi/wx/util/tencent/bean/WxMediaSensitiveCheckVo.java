package com.ruoyi.wx.util.tencent.bean;

public class WxMediaSensitiveCheckVo {
    private String openid;//用户openid，2小时内访问过
    private String media_url; //要检测的图片或音频的url，支持图片格式包括jpg, jepg, png, bmp, gif（取首帧），支持的音频格式包括mp3, aac, ac3, wma, flac, vorbis, opus, wav
    private int version =2;//接口版本号，2.0版本为固定值2
    private int scene=3;//	场景枚举值（1 资料；2 评论；3 论坛；4 社交日志）
    private int media_type=2; //1:音频;2:图片

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getScene() {
        return scene;
    }

    public void setScene(int scene) {
        this.scene = scene;
    }

    public int getMedia_type() {
        return media_type;
    }

    public void setMedia_type(int media_type) {
        this.media_type = media_type;
    }
}
