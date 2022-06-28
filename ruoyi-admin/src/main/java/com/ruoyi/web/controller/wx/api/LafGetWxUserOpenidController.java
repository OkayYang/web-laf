package com.ruoyi.web.controller.wx.api;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.wx.service.TencentService;
import com.ruoyi.wx.util.bean.wx.WxRespResult;
import com.ruoyi.wx.util.token.JwtUtils;
import com.ruoyi.wx.domain.LafStudent;
import com.ruoyi.wx.util.bean.wx.WxUserModel;
import com.ruoyi.wx.service.ILafStudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("wx/api/login")
public class LafGetWxUserOpenidController extends WxBaseController {
    @Autowired
    private ILafStudentService lafStudentService;

    @Autowired
    private TencentService tencentService;

    /**
     * 评委测试接口
     * @param code
     * @return
     * @throws Exception
     */
    @GetMapping("/beta")
    @ResponseBody
    @RepeatSubmit(interval = 500, message = "请求过于频繁")
    public WxRespResult beta(@Param("code") String code) throws Exception {
        String token=null;
        LafStudent student=null;
        if (code!=null){
            if ("611612".equals(code)){
                student = new LafStudent();
                student.setOpenid("okNNi5Gwllsb-YdNgHYqrGDFH8BY");
                List<LafStudent> list = lafStudentService.selectLafStudentList(student);
                if (list.size()>0){
                    student=list.get(0);
                    token = JwtUtils.createToken(student.getStuId(),student.getOpenid());
                }
            }
        }
        if (token == null){
            return error("登陆失败!");
        }else {
            return success(student,token);
        }
    }

    @PostMapping("/check")
    @ResponseBody
    @RepeatSubmit(interval = 500, message = "请求过于频繁")
    public WxRespResult checkLogin(@RequestBody WxUserModel wxUserModel) throws Exception {
        System.out.println(wxUserModel);
        LafStudent student = new LafStudent();
        String token =null;
        String openId =  tencentService.getUserOpenId(wxUserModel.getCode());
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

                LafStudent stu = new LafStudent();
                stu.setStuId(student.getStuId());
                stu.setStuImage(wxUserModel.getAvatarUrl());
                stu.setStuSex(wxUserModel.getGender());
                stu.setStuNick(wxUserModel.getNickName());
                if (lafStudentService.updateLafStudent(stu)==1) {
                    student.setStuNick(wxUserModel.getNickName());
                    student.setStuImage(wxUserModel.getAvatarUrl());
                    stu.setStuSex(wxUserModel.getGender());
                }
            }
            token = JwtUtils.createToken(student.getStuId(),student.getOpenid());

        }
        if (token == null){
            return error("登陆失败!");
        }else {
            return success(student,token);
        }
    }



}
