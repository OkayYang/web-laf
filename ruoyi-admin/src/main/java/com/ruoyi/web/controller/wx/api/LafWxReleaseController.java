package com.ruoyi.web.controller.wx.api;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.WxBaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.WxLoginResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.service.ILafReleaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
    public WxLoginResult addCheck(@RequestBody LafRelease lafRelease)
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

    /**
     * wx查询帖子列表接口
     * 开放接口无需权限
     */
    @ApiOperation("wx用户获取失物招领列表")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@ApiParam(value = "查询帖子信息", required = true)LafRelease lafRelease)
    {
        startPage();
        List<LafRelease> list = lafReleaseService.selectLafReleaseList(lafRelease);
        return getDataTable(list);
    }

    /**
     * wx 查询接口
     * @param lafRelease
     * @return
     */
    @PostMapping("/select")
    @ResponseBody
    public TableDataInfo select(@RequestBody LafRelease lafRelease)
    {
        startPage();
        List<LafRelease> list = lafReleaseService.selectLafReleaseList(lafRelease);
        return getDataTable(list);
    }
    /**
     * wx 查询个人发布
     */
    @ResponseBody
    @GetMapping("/auth/publish")
    public TableDataInfo myRelease(@Param("id")String id){
        startPage();
        LafRelease lafRelease = new LafRelease();
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
    public WxLoginResult remove(@RequestParam("tid") String tid)
    {
        return toAjax(lafReleaseService.deleteLafReleaseByRelIds(tid));
    }


}
