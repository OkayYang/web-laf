package com.ruoyi.wx.util.token;

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
import org.springframework.stereotype.Service;

@Service("tokenTask")
public class AccessTokenTask {
    @Autowired
    private LafApiTokenMapper lafApiTokenMapper;


    private static String appid = "wx8a13127914667c1c";
    private static String secret = "41ddaea423916a416d377cec14345791";
    public String getAccessToken(){
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
            if (accessToken!=null){
                LafApiToken lafApiToken = new LafApiToken();
                lafApiToken.setToken(accessToken);
                lafApiToken.setId(1L);
                lafApiToken.setCreateTime(DateUtils.getNowDate());
                lafApiTokenMapper.updateLafApiToken(lafApiToken);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public  String getOcrAccessToken(){
        String accessToken = null;
        String path = "https://aip.baidubce.com/oauth/2.0/token";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);


        // 设置header参数
        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");

        // 设置query参数
        request.addQueryParameter("client_id", "BFWxODNsE1CPwIFKGkP38oUl");
        request.addQueryParameter("client_secret", "4vFC4fxonGMi9CXMmt1pKmmwOOBwSK0y");
        request.addQueryParameter("grant_type", "client_credentials");

        ApiExplorerClient client = new ApiExplorerClient();

        try {
            ApiExplorerResponse response = client.sendRequest(request);
            // 返回结果格式为Json字符串
            org.json.JSONObject object= new org.json.JSONObject(response.getResult());
            accessToken = object.getString("access_token");

            if (accessToken!=null){
                LafApiToken lafApiToken = new LafApiToken();
                lafApiToken.setToken(accessToken);
                lafApiToken.setId(2L);
                lafApiToken.setCreateTime(DateUtils.getNowDate());
                lafApiTokenMapper.updateLafApiToken(lafApiToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;

    }
}
