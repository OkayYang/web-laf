package com.ruoyi.web.controller.wx.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.domain.LafClaimStudent;
import com.ruoyi.wx.domain.LafStudent;
import com.ruoyi.wx.service.ILafStudentService;
import com.ruoyi.wx.service.TencentService;
import com.ruoyi.wx.util.bean.wx.WxRespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wx/api/student")
public class LafWxStudentController extends WxBaseController {
    @Autowired
    private ILafStudentService lafStudentService;
    @Autowired
    private TencentService tencentService;

    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PostMapping("/auth/edit")
    @ResponseBody
    public WxRespResult editSave(@RequestBody LafStudent lafStudent)
    {
        lafStudent.setStuId(getWxUid());
        String text = lafStudent.getStuNick()+lafStudent.getStuQq();
        if (tencentService.checkText(text,getOpenid(),1)==100) {
            return toAjax(lafStudentService.updateLafStudent(lafStudent));
        }else {
            return WxRespResult.sensitive();
        }

    }
    @GetMapping("/auth/my")
    @ResponseBody
    public LafStudent my(){
        Long id = getWxUid();
        return lafStudentService.selectLafStudentByStuId(id);
    }
    @GetMapping("/list")
    @ResponseBody
    public LafClaimStudent list(@RequestParam("uid")Long uid){
        return lafStudentService.selectLafClaimStudentList(uid);

    }


}
