package com.ruoyi.web.controller.wx.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.domain.LafStudent;
import com.ruoyi.wx.service.ILafStudentService;
import com.ruoyi.wx.util.WxRespResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wx/api/student")
public class LafWxStudentController extends WxBaseController {
    @Autowired
    private ILafStudentService lafStudentService;

    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PostMapping("/auth/edit")
    @ResponseBody
    public WxRespResult editSave(@RequestBody LafStudent lafStudent)
    {
        lafStudent.setStuId(getWxUid());

        return toAjax(lafStudentService.updateLafStudent(lafStudent));
    }

    /**
     * 删除学生
     */
    @Log(title = "学生", businessType = BusinessType.DELETE)
    @PostMapping( "/auth/remove")
    @ResponseBody
    public WxRespResult remove(String ids)
    {

        return toAjax(lafStudentService.deleteLafStudentByStuIds(ids));
    }
}
