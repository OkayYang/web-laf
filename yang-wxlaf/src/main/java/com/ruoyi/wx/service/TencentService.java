package com.ruoyi.wx.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

/**
 * 调用腾讯一些api服务
 */
public interface TencentService {

    /**
     * 上传图片到COS对象存储
     * @param file  图片地址
     * @param request
     * @param response
     * @return  响应结果
     */
    public Map<String, Object> ContentCOS(File file, HttpServletRequest request, HttpServletResponse response);

    /**
     * 微信用户登陆调用微信api
     * @param code 用户凭证code换取open-id
     * @return open-id
     */
    public  String getUserOpenId(String code);
}
