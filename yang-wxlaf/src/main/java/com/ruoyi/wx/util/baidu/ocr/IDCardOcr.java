package com.ruoyi.wx.util.baidu.ocr;


import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.google.gson.Gson;
import com.ruoyi.wx.util.WxOrcIDCardResult;
import com.ruoyi.wx.util.baidu.BaiduOcrSfzResult;
import com.ruoyi.wx.util.baidu.Unit;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class IDCardOcr {
    private static String accessToken  = "24.d95dfacbfad875c9559c065822a7e4d9.2592000.1648808603.282335-25693405";

    public static String getOcrAccessToken(){
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
            JSONObject object= new JSONObject(response.getResult());
            accessToken = object.getString("access_token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;

    }
    public static boolean baiDuIDCardOcr(WxOrcIDCardResult wxOrcIDCardResult,String imageUrl,String imagePath){
        boolean flag =false;
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
                sfzAddWaterMark(imagePath,baiduOcrSfzResult);
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
    public static String idCardType(String imageUrl){
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
    static boolean sfzAddWaterMark(String imagePath,BaiduOcrSfzResult baiduOcrSfzResult) throws IOException {

        // 返回结果格式为Json字符串
        List<Unit> unitList = new ArrayList<>();
        unitList.add(new Unit(baiduOcrSfzResult.getPhotoLocation(),"头像"));
        unitList.add(new Unit(baiduOcrSfzResult.getWordsResult().getBirth().getLocation(), baiduOcrSfzResult.getWordsResult().getBirth().getWords()));
        unitList.add(new Unit(baiduOcrSfzResult.getWordsResult().getNumber().getLocation(), baiduOcrSfzResult.getWordsResult().getNumber().getWords()));
        unitList.add(new Unit(baiduOcrSfzResult.getWordsResult().getAddress().getLocation(), baiduOcrSfzResult.getWordsResult().getAddress().getWords()));

        WxOrcIDCardResult wxOrcIDCardResult = new WxOrcIDCardResult();
        wxOrcIDCardResult.setName(baiduOcrSfzResult.getWordsResult().getName().getWords());
        wxOrcIDCardResult.setNumber(baiduOcrSfzResult.getWordsResult().getNumber().getWords());
        return WaterMark.sfzMosaic(imagePath,unitList);

    }
}
