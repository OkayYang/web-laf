package com.ruoyi.wx.util.tencent;

import com.alibaba.fastjson.JSONObject;
import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.wx.domain.LafApiToken;
import com.ruoyi.wx.mapper.LafApiTokenMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



public class TencentWxApi {

    private static String appid = "wx39b85c8911cd3be5";
    private static String secret = "8ce39fb09a038085229da0450accf6e3";

    /**
     *
     * @param code 用户凭证code换取open-id
     * @return open-id
     */
    public static String getUserOpenId(String code){

        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid="+appid);//appid设置
        url.append("&secret="+secret);//secret设置
        url.append("&js_code=");//code设置
        url.append(code);
        url.append("&grant_type=authorization_code");
        String openId = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
            JSONObject res = JSONObject.parseObject(content);//把信息封装为json
            openId = (String) res.get("openid");//拿到open-id

        } catch (Exception e) {
            e.printStackTrace();
        }
        return openId;
    }




}
