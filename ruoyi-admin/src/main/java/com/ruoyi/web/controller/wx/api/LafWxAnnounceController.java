package com.ruoyi.web.controller.wx.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.wx.domain.LafAnnounce;
import com.ruoyi.wx.domain.LafWxMessage;
import com.ruoyi.wx.domain.LafWxRelease;
import com.ruoyi.wx.service.ILafAnnounceService;
import com.ruoyi.wx.service.ILafWxMessageService;
import com.ruoyi.wx.util.bean.wx.WxRespResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wx/api/announce")
public class LafWxAnnounceController extends WxBaseController {

    @Autowired
    private ILafAnnounceService lafAnnounceService;
    @Autowired
    private ILafWxMessageService lafWxMessageService;

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

    @GetMapping("/auth/msg")
    @ResponseBody
    public PageInfo<LafWxMessage> list(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<LafWxMessage> list = lafWxMessageService.selectLafWxMessageList(getWxUid());
        PageInfo<LafWxMessage> pageInfo = new PageInfo<LafWxMessage>(list,pageSize);
        return pageInfo;
    }

}
