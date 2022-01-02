package com.ruoyi.web.controller.wx.api;

import com.ruoyi.wx.domain.LafAnnounce;
import com.ruoyi.wx.service.ILafAnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public LafAnnounce list(){
        LafAnnounce lafAnnounce = new LafAnnounce();
        lafAnnounce.setStatus("0");
        List<LafAnnounce> list= lafAnnounceService.selectLafAnnounceList(lafAnnounce);
        if (list.size()>0){
            lafAnnounce = list.get(0);
        }
        return lafAnnounce;
    }

}
