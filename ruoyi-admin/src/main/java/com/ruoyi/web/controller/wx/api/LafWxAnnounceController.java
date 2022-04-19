package com.ruoyi.web.controller.wx.api;

import com.ruoyi.wx.domain.LafAnnounce;
import com.ruoyi.wx.service.ILafAnnounceService;
import com.ruoyi.wx.util.bean.wx.WxRespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/wx/api/announce")
public class LafWxAnnounceController extends WxBaseController {

    @Autowired
    private ILafAnnounceService lafAnnounceService;

    /**
     * 小程序用户获得公告
     */
    @GetMapping("/list")
    @ResponseBody
    public List<LafAnnounce> list(){
        LafAnnounce lafAnnounce = new LafAnnounce();
        lafAnnounce.setStatus("1");
        return lafAnnounceService.selectLafAnnounceList(lafAnnounce);
    }
    @GetMapping("/detail")
    @ResponseBody
    public LafAnnounce list(@RequestParam("sid") Long sid){
        return lafAnnounceService.selectLafAnnounceByAnnId(sid);
    }

    @GetMapping("/skim")
    @ResponseBody
    public WxRespResult skim(@RequestParam("sid") Long sid){
        return toAjax(lafAnnounceService.updateBrowse(sid));
    }

}
