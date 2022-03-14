package com.ruoyi.web.controller.wx.api;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.wx.util.WxRespResult;
import com.ruoyi.wx.util.tencent.TencentWxApi;
import com.ruoyi.wx.util.token.JwtUtils;
import com.ruoyi.wx.domain.LafStudent;
import com.ruoyi.wx.util.WxUserModel;
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
@RequestMapping("wx/api/login")
public class LafGetWxUserOpenidController extends WxBaseController {
    @Autowired
    private ILafStudentService lafStudentService;

    @PostMapping("/check")
    @ResponseBody
    public WxRespResult checkLogin(@RequestBody WxUserModel wxUserModel) throws Exception {
        System.out.println(wxUserModel);
        LafStudent student = new LafStudent();
        String token =null;
        String openId =  TencentWxApi.getUserOpenId(wxUserModel.getCode());
        if (openId!=null){
            student.setOpenid(openId);
            List<LafStudent> studentList = lafStudentService.selectLafStudentList(student);
            int count = studentList.size();
            System.out.println(studentList.size());
            if (count ==0){
                //未注册
                student.setStuImage(wxUserModel.getAvatarUrl());
                student.setStuNick(wxUserModel.getNickName());
                student.setStuSex(wxUserModel.getGender());
                System.out.println(student);
                lafStudentService.insertLafStudent(student);
            }else {
                student=studentList.get(0);
            }
            token = JwtUtils.createToken(student.getStuId());

        }
        if (token == null){
            return error("登陆失败!");
        }else {
            return success(student,token);
        }
    }



}
