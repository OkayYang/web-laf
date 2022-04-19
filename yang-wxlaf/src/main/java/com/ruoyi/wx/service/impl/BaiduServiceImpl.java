package com.ruoyi.wx.service.impl;

import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.google.gson.Gson;
import com.ruoyi.wx.domain.LafApiToken;
import com.ruoyi.wx.mapper.LafApiTokenMapper;
import com.ruoyi.wx.service.BaiduService;
import com.ruoyi.wx.util.HttpUtil;
import com.ruoyi.wx.util.baidu.WaterMark;
import com.ruoyi.wx.util.baidu.bean.BaiduOcrSfzResult;
import com.ruoyi.wx.util.baidu.bean.ClassifierData;
import com.ruoyi.wx.util.baidu.bean.ClassifierRet;
import com.ruoyi.wx.util.baidu.bean.Location;
import com.ruoyi.wx.util.bean.wx.WxOrcIDCardResult;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaiduServiceImpl implements BaiduService {

    @Autowired
    private LafApiTokenMapper lafApiTokenMapper;

    /**
     * 判断卡片类别
     * @param imageUrl 卡片图片url
     * @return
     */
    @Override
    public String judgeIdCardType(String imageUrl) {
        LafApiToken apiToken = lafApiTokenMapper.selectLafApiTokenById(2L);
        String accessToken = apiToken.getToken();
        String type = null;
        String path = "https://aip.baidubce.com/rest/2.0/ocr/v1/multi_card_classify";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);


        // 设置header参数
        request.addHeaderParameter("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        // 设置query参数
        request.addQueryParameter("access_token", accessToken);

        // 设置jsonBody参数
        String jsonBody = "url="+imageUrl;
        request.setJsonBody(jsonBody);

        ApiExplorerClient client = new ApiExplorerClient();

        try {
            ApiExplorerResponse response = client.sendRequest(request);
            JSONObject object= new JSONObject(response.getResult());
            // 返回结果格式为Json字符串
            if (object.has("result")) {
                object = object.getJSONArray("result").getJSONObject(0);
                if (object.has("card_type")){

                    if ("idcard_front".equals(object.getString("card_type"))){
                        type ="sfz";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    /**
     * 通用卡证识别
     * @param wxOrcIDCardResult  ocr识别结果
     * @param imageUrl   卡证url
     * @param imagePath  打码的图片,和imageUrl为同一张
     * @return
     */
    @Override
    public boolean classRecognize(WxOrcIDCardResult wxOrcIDCardResult, String imageUrl, String imagePath) {
        boolean flag = false;
        LafApiToken apiToken = lafApiTokenMapper.selectLafApiTokenById(2L);
        String accessToken = apiToken.getToken();
        // iocr识别apiUrl
        String recogniseUrl = "https://aip.baidubce.com/rest/2.0/solution/v1/iocr/recognise";

        try {
            // 请求分类器参数
            String classifierParams = "classifierId=1&url="+imageUrl;
            // 请求模板识别
            String result = HttpUtil.post(recogniseUrl, accessToken, classifierParams);
            System.out.println(result);
            JSONObject jsonObject = new JSONObject(result);
            Gson gson = new Gson();
            String code = jsonObject.getString("error_code");

            if ("0".equals(code)) {
                ClassifierData classifierData = gson.fromJson(jsonObject.getString("data"),ClassifierData.class);
                if ("id_card_front".equals(classifierData.getTemplateSign())){
                    //调用身份证坐标识别api进行打码
                    flag = idCardOcr(wxOrcIDCardResult,imageUrl,imagePath);
                    //System.out.println("身份证");
                }
                else if ("4068a004b22c533721d38896a264e727".equals(classifierData.getTemplateSign())||"1f4ee1675e553439f8ec6cdf74568242".equals(classifierData.getTemplateSign())){
                    //System.out.println("学生证");
                    //设置返回类别
                    wxOrcIDCardResult.setType("xsz");
                    List<Location> locationList = new ArrayList<>();
                    List<ClassifierRet> list = classifierData.getRet();
                    for (int i = 0; i < list.size(); i++) {
                        String wordName = list.get(i).getName();
                        if ("xh".equals(wordName)){
                            //设置返回学号
                            wxOrcIDCardResult.setNumber(list.get(i).getWord());
                            locationList.add(list.get(i).getLocation());
                        }
                        if ("photo".equals(wordName)){
                            locationList.add(list.get(i).getLocation());
                        }
                        if ("name".equals(wordName)){
                            //设置返回学号
                            wxOrcIDCardResult.setName(list.get(i).getWord());
                        }
                    }
                    //打码
                    flag = WaterMark.sfzMosaic(imagePath,locationList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 身份证识别
     * @param wxOrcIDCardResult 身份证识别成功后返回字段信息
     * @param imageUrl   身份证图片链接
     * @param imagePath   打码的图片,和imageUrl为同一张
     * @return
     */
    @Override
    public boolean idCardOcr(WxOrcIDCardResult wxOrcIDCardResult, String imageUrl, String imagePath) {
        boolean flag =false;

        LafApiToken apiToken = lafApiTokenMapper.selectLafApiTokenById(2L);
        String accessToken = apiToken.getToken();
        String path = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        String isDetectPhoto = "true";
        String error_code = null;
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);

        // 设置header参数
        request.addHeaderParameter("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        // 设置query参数
        request.addQueryParameter("access_token", accessToken);
        request.addQueryParameter("detect_photo", isDetectPhoto);

        // 设置jsonBody参数
        String jsonBody = "url="+imageUrl+"&id_card_side=front";
        request.setJsonBody(jsonBody);

        ApiExplorerClient client = new ApiExplorerClient();

        try {
            ApiExplorerResponse response = client.sendRequest(request);

            JSONObject object= new JSONObject(response.getResult());


            if (object.has("error_code")) {
                error_code = object.getString("error_code");
            }

            if (error_code==null){
                //打码
                Gson gson = new Gson();
                BaiduOcrSfzResult baiduOcrSfzResult = gson.fromJson(response.getResult(), BaiduOcrSfzResult.class);
                flag = addWaterMark(imagePath,baiduOcrSfzResult);
                wxOrcIDCardResult.setName(baiduOcrSfzResult.getWordsResult().getName().getWords());
                wxOrcIDCardResult.setNumber(baiduOcrSfzResult.getWordsResult().getNumber().getWords());
                wxOrcIDCardResult.setType("sfz");
            }else {
                System.out.println(error_code+"图片或token有问题");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 证件打码
     * @param imagePath
     * @param baiduOcrSfzResult
     * @return
     * @throws IOException
     */
    protected boolean addWaterMark(String imagePath, BaiduOcrSfzResult baiduOcrSfzResult) throws IOException {

        // 返回结果格式为Json字符串
        List<Location> unitList = new ArrayList<>();
        unitList.add(baiduOcrSfzResult.getPhotoLocation());
        unitList.add(baiduOcrSfzResult.getWordsResult().getBirth().getLocation());
        unitList.add(baiduOcrSfzResult.getWordsResult().getNumber().getLocation());
        unitList.add(baiduOcrSfzResult.getWordsResult().getAddress().getLocation());

        WxOrcIDCardResult wxOrcIDCardResult = new WxOrcIDCardResult();
        wxOrcIDCardResult.setName(baiduOcrSfzResult.getWordsResult().getName().getWords());
        wxOrcIDCardResult.setNumber(baiduOcrSfzResult.getWordsResult().getNumber().getWords());

        return WaterMark.sfzMosaic(imagePath,unitList);

    }
}
