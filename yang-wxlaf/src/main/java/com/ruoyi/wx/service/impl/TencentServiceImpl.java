package com.ruoyi.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.ruoyi.wx.service.TencentService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: tlw
 * @date: 2021年10月12日
 * @Description: COS对象存储
 */
@Service
public class TencentServiceImpl implements TencentService {
    private static final Logger LOG = LoggerFactory.getLogger(TencentServiceImpl.class);

    @Value("${cos.accessKey}")
    private String accessKey;

    @Value("${cos.secretKey}")
    private String secretKey;

    @Value("${cos.regionName}")
    private String regionName;

    @Value("${cos.bucketName}")
    private String bucketName;

    @Value("${cos.keyName}")
    private String keyName;

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    /**
     * 上次图片对接腾讯CDN
     *
     * @param file  文件地址
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> ContentCOS(File file, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);

        // 获取文件类型
        String name = file.getName();
        String fileType = name.substring(name.lastIndexOf(".") + 1);

        // bucket名需包含appid
        String key = keyName+ name;
        String url = null;
        try {

            // 报错请求对象
            AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName, key, file);
            // 设置节点
            appendObjectRequest.setPosition(0L);
            AppendObjectResult appendObjectResult = cosclient.appendObject(appendObjectRequest);
            // 文件大小
            long nextAppendPosition = appendObjectResult.getNextAppendPosition();
            LOG.info("文件大小：{}", nextAppendPosition);

            // 获取返回对象
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
            COSObject cosObject = cosclient.getObject(getObjectRequest);
            url = cosObject.getObjectContent().getHttpRequest().getURI().toString();
            LOG.info("COS对象地址：{}", url);
            map.put("file_path", url);
            map.put("success", true);
            map.put("msg", "上传成功");
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
            cosclient.shutdown();
        }
        return map;
    }

    /**
     * 微信用户登陆调用微信api
     * @param code 用户凭证code换取open-id
     * @return open-id
     */
    @Override
    public String getUserOpenId(String code) {
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
