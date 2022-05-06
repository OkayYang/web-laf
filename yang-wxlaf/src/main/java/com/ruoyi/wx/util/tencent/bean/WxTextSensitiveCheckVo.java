package com.ruoyi.wx.util.tencent.bean;

public class WxTextSensitiveCheckVo {
    private String openid;//用户openid，2小时内访问过
    private String content; //检测文本内容
    private int version =2;//接口版本号，2.0版本为固定值2
    private int scene=3;//	场景枚举值（1 资料；2 评论；3 论坛；4 社交日志）



    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

}
