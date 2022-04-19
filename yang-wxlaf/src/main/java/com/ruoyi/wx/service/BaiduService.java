package com.ruoyi.wx.service;

import com.ruoyi.wx.util.bean.wx.WxOrcIDCardResult;

/**
 * 调用百度提供的api接口
 */
public interface BaiduService {

    /**
     * 判断卡片类别
     * @param imageUrl 卡片图片url
     * @return
     */
    public String judgeIdCardType(String imageUrl);

    /**
     * 通用卡证识别
     * @param wxOrcIDCardResult  ocr识别结果
     * @param imageUrl   卡证url
     * @param imagePath  打码的图片,和imageUrl为同一张
     * @return
     */
    public boolean classRecognize(WxOrcIDCardResult wxOrcIDCardResult, String imageUrl, String imagePath);

    /**
     * 身份证识别
     * @param wxOrcIDCardResult 身份证识别成功后返回字段信息
     * @param imageUrl   身份证图片链接
     * @param imagePath   打码的图片,和imageUrl为同一张
     * @return
     */
    public boolean idCardOcr(WxOrcIDCardResult wxOrcIDCardResult, String imageUrl, String imagePath);
}
