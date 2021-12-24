package com.ruoyi.web.controller.wx.web;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.WxBaseController;
import com.ruoyi.common.core.domain.WxLoginResult;
import com.ruoyi.common.utils.wx.JwtUtils;
import com.ruoyi.wx.domain.LafStudent;
import com.ruoyi.common.utils.wx.WeChatModel;
import com.ruoyi.wx.service.ILafStudentService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("wx/login")
public class LafGetWxUserOpenidController extends WxBaseController {
    @Autowired
    private ILafStudentService lafStudentService;

    @PostMapping("/check")
    @ResponseBody
    public WxLoginResult checkLogin(@RequestBody WeChatModel weChatModel){
        System.out.println(weChatModel);

        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=wx8a13127914667c1c");//appid设置
        url.append("&secret=41ddaea423916a416d377cec14345791");//secret设置
        url.append("&js_code=");//code设置
        url.append(weChatModel.getCode());
        url.append("&grant_type=authorization_code");
        LafStudent student = new LafStudent();
        String content="";
        String token="";
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            content = EntityUtils.toString(result);
            JSONObject res = JSONObject.parseObject(content);//把信息封装为json
            String openId = (String) res.get("openid");
            if (openId!=null){
                student.setOpenid(openId);
                List<LafStudent> studentList = lafStudentService.selectLafStudentList(student);
                int count = studentList.size();
                System.out.println(studentList.size());
                if (count ==0){
                    //未注册
                    student.setStuImage(weChatModel.getAvatarUrl());
                    student.setStuNick(weChatModel.getNickName());
                    student.setStuSex(weChatModel.getGender());
                    System.out.println(student);
                    lafStudentService.insertLafStudent(student);
                }else {
                    student=studentList.get(0);
                }
                token = JwtUtils.createToken(student.getStuId());

            }else {
                return error("登陆失败!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success(student,token);
    }



}
