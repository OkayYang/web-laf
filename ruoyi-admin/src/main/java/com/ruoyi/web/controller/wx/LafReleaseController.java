package com.ruoyi.web.controller.wx;

import java.io.File;
import java.util.List;

import com.ruoyi.common.core.controller.WxBaseController;
import com.ruoyi.common.core.domain.WxLoginResult;
import com.ruoyi.wx.service.ILafStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.service.ILafReleaseService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 帖子Controller
 * 
 * @author yang
 * @date 2021-10-05
 */
@Api("失物招领帖子管理")
@Controller
@RequestMapping("/wx/release")
public class LafReleaseController extends WxBaseController
{
    private String prefix = "wx/release";

    @Value("${wx.upload.path}")
    private String imagePath;

    @Autowired
    private ILafReleaseService lafReleaseService;
    @Autowired
    private ILafStudentService lafStudentService;

    @RequiresPermissions("wx:release:view")
    @GetMapping()
    public String release()
    {
        return prefix + "/release";
    }

    /**
     * 查询帖子列表
     * 开放接口无需权限
     */
    @ApiOperation("wx用户获取失物招领列表")

    /*@RequiresPermissions("wx:release:list")*/
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
        //搜索具体某一天
        if (lafRelease.getCreateTime()!=null){
            System.out.println(lafRelease.getCreateTime());
        }
        List<LafRelease> list = lafReleaseService.selectLafReleaseList(lafRelease);
        return getDataTable(list);
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
     * 导出帖子列表
     */
    @RequiresPermissions("wx:release:export")
    @Log(title = "帖子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LafRelease lafRelease)
    {
        List<LafRelease> list = lafReleaseService.selectLafReleaseList(lafRelease);
        ExcelUtil<LafRelease> util = new ExcelUtil<LafRelease>(LafRelease.class);
        return util.exportExcel(list, "帖子数据");
    }

    /**
     * 新增帖子
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存帖子
     * @return
     */
    @RequiresPermissions("wx:release:add")
    @Log(title = "帖子", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public WxLoginResult addSave(LafRelease lafRelease)
    {
        return toAjax(lafReleaseService.insertLafRelease(lafRelease));
    }

    /**
     * wx 发布帖子接口
     * @param lafRelease
     * @return
     */

    @PostMapping("/add/check")
    @ResponseBody
    public WxLoginResult addCheck(@RequestBody LafRelease lafRelease)
    {
        Long wxUid = getWxUid();
        if (wxUid!=null) {
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
        }else return error("未登录!");

    }



    /**
     * 修改帖子
     */
    @GetMapping("/edit/{relId}")
    public String edit(@PathVariable("relId") Long relId, ModelMap mmap)
    {
        LafRelease lafRelease = lafReleaseService.selectLafReleaseByRelId(relId);
        mmap.put("lafRelease", lafRelease);
        return prefix + "/edit";
    }

    /**
     * 修改保存帖子
     * @return
     */
    @RequiresPermissions("wx:release:edit")
    @Log(title = "帖子", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public WxLoginResult editSave(LafRelease lafRelease)
    {
        return toAjax(lafReleaseService.updateLafRelease(lafRelease));
    }

    /**
     * 删除帖子
     * @return
     */
    @RequiresPermissions("wx:release:remove")
    @Log(title = "帖子", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public WxLoginResult remove(String ids)
    {
        return toAjax(lafReleaseService.deleteLafReleaseByRelIds(ids));
    }
}
