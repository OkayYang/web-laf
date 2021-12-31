package com.ruoyi.web.controller.wx;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.service.ILafReleaseService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 帖子Controller
 *
 * @author yang
 * @date 2021-12-26
 */
@Controller
@RequestMapping("/wx/release")
public class LafReleaseController extends BaseController
{
    private String prefix = "wx/release";

    @Autowired
    private ILafReleaseService lafReleaseService;

    @RequiresPermissions("wx:release:view")
    @GetMapping()
    public String release()
    {
        return prefix + "/release";
    }

    /**
     * 查询帖子列表
     */
    @RequiresPermissions("wx:release:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LafRelease lafRelease)
    {
        startPage();
        List<LafRelease> list = lafReleaseService.selectLafReleaseList(lafRelease);
        return getDataTable(list);
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
     */
    @RequiresPermissions("wx:release:add")
    @Log(title = "帖子", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LafRelease lafRelease)
    {
        return toAjax(lafReleaseService.insertLafRelease(lafRelease));
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
     */
    @RequiresPermissions("wx:release:edit")
    @Log(title = "帖子", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LafRelease lafRelease)
    {
        return toAjax(lafReleaseService.updateLafRelease(lafRelease));
    }

    /**
     * 删除帖子
     */
    @RequiresPermissions("wx:release:remove")
    @Log(title = "帖子", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(lafReleaseService.deleteLafReleaseByRelIds(ids));
    }
}