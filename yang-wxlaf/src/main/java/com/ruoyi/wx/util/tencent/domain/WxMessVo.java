package com.ruoyi.wx.util.tencent.domain;

import java.util.Map;

public class WxMessVo {
    private String touser;//用户openid
    private String template_id;//订阅消息模版id
    private String page ;//默认跳到小程序首页
    private Map<String, TemplateData> data;//推送文字

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }
}
