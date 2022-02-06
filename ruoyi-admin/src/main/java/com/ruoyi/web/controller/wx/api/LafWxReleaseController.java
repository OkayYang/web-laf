package com.ruoyi.web.controller.wx.api;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.wx.util.WxRespResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.service.ILafReleaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/wx/api/release")
public class LafWxReleaseController extends WxBaseController {

    @Value("${wx.upload.path}")
    private String imagePath;

    @Autowired
    private ILafReleaseService lafReleaseService;
    /**
     * wx 发布帖子接口
     * @param lafRelease
     * @return
     */

    @PostMapping("/auth/add/check")
    @ResponseBody
    public WxRespResult addCheck(@RequestBody LafRelease lafRelease)
    {
        lafRelease.setCreateId(getWxUid());
        if (lafRelease.getRelImage() == null) {
            if ("1".equals(lafRelease.getRelSatus())) {
                lafRelease.setRelImage("/img/user/shiwu.png");
            }
            if ("2".equals(lafRelease.getRelSatus())) {
                lafRelease.setRelImage("/img/user/xunwu.png");
            }
        }
        return toAjax(lafReleaseService.insertLafRelease(lafRelease));

    }
    /**
     * wx  上传接口
     * @param file
     * @return   返回帖子图片地址
     * @throws Exception
     */

    @ResponseBody
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file ) throws Exception{
        //fileName是你前台传参时的文件名字，也可以不指定
        String fileName = file.getOriginalFilename();
        //不指定名字，保存时使用 file.getOriginalFilename()得到文件名字
        //保存到文件服务器，OSS服务器
        file.transferTo(new File(this.imagePath+fileName));
        return "/img/user/tiezi/"+fileName;
    }



    @PostMapping("/list")
    @ResponseBody
    public PageInfo<LafRelease> list(@RequestBody LafRelease lafRelease,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        //过滤已被删除的
        lafRelease.setRelDel("1");
        List<LafRelease> list = lafReleaseService.selectLafReleaseList(lafRelease);
        PageInfo<LafRelease> pageInfo = new PageInfo<LafRelease>(list,pageSize);
        return pageInfo;
    }


    /**
     * wx 查询个人发布
     */
    @ResponseBody
    @GetMapping("/auth/publish")
    public TableDataInfo myRelease(@Param("id")String id){
        startPage();
        LafRelease lafRelease = new LafRelease();
        lafRelease.setRelDel("1");
        lafRelease.setCreateId(getWxUid());
        if ("1".equals(id)||"2".equals(id)){
            lafRelease.setRelSatus(id);
        }
        if("3".equals(id)){
            lafRelease.setRelFlag("2");
        }
        List<LafRelease> list = lafReleaseService.selectLafReleaseList(lafRelease);
        return getDataTable(list);
    }
    /**
     * 微信用户删除
     * @param tid
     * @return
     */

    @Log(title = "帖子", businessType = BusinessType.DELETE)
    @GetMapping( "/auth/remove")
    @ResponseBody
    public WxRespResult remove(@RequestParam("tid") String tid)
    {
        return toAjax(lafReleaseService.deleteLafReleaseByRelIds(tid));
    }

    /**
     * 微信用户删除
     */
    @Log(title = "帖子", businessType = BusinessType.UPDATE)
    @PostMapping("/auth/edit")
    @ResponseBody
    public WxRespResult editSave(@RequestBody LafRelease lafRelease)
    {

        return toAjax(lafReleaseService.updateLafRelease(lafRelease));
    }


}
