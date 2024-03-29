package com.ruoyi.web.controller.wx.api;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.wx.domain.LafPush;
import com.ruoyi.wx.service.ILafPushService;
import com.ruoyi.wx.util.bean.wx.WxRespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/wx/api/push")
@Controller
public class LafWxPushController extends WxBaseController{
    @Autowired
    private ILafPushService lafPushService;

    @PostMapping("/auth/add")
    @ResponseBody
    public WxRespResult addSave(@RequestBody LafPush lafPush)
    {

        lafPush.setStuId(getWxUid());
        if (lafPushService.selectLafPushList(lafPush).size()>5) {
            return error("抱歉,超过最大限制！");
        }

        return toAjax(lafPushService.insertLafPush(lafPush));
    }
    @PostMapping("/auth/update")
    @ResponseBody
    public WxRespResult edit(@RequestBody LafPush lafPush)
    {
        return toAjax(lafPushService.updateLafPush(lafPush));
    }

    @GetMapping("/auth/list")
    @ResponseBody
    public TableDataInfo list()
    {
        LafPush lafPush = new LafPush();
        lafPush.setStuId(getWxUid());
        lafPush.setPushDel("1");
        List<LafPush> list = lafPushService.selectLafPushList(lafPush);
        return getDataTable(list);
    }
    @GetMapping("/auth/del")
    @ResponseBody
    public WxRespResult del(@RequestParam("pid") Long pid){
        return toAjax(lafPushService.deleteLafPushByPushId(pid));
    }


}
