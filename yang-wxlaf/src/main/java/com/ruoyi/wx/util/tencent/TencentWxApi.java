package com.ruoyi.wx.util.tencent;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class TencentWxApi {

    private static String appid = "wx8a13127914667c1c";
    private static String secret = "41ddaea423916a416d377cec14345791";

    /**
     *
     * @param code 用户凭证code换取open-id
     * @return open-id
     */
    static String getUserOpenId(String code){

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

    static String getAccessToken(){
        String accessToken = null;
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&");
        url.append("appid=" + appid);//appid设置
        url.append("&secret=" + secret);//secret设置

        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
            JSONObject res = JSONObject.parseObject(content);//把信息封装为json
            System.out.println(res);
            accessToken = (String) res.get("access_token");//拿到open-id

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }


}
