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
import com.ruoyi.wx.domain.LafPush;
import com.ruoyi.wx.service.ILafPushService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 失物推送Controller
 * 
 * @author yang
 * @date 2022-03-24
 */
@Controller
@RequestMapping("/wx/push")
public class LafPushController extends BaseController
{
    private String prefix = "wx/push";

    @Autowired
    private ILafPushService lafPushService;

    @RequiresPermissions("wx:push:view")
    @GetMapping()
    public String push()
    {
        return prefix + "/push";
    }

    /**
     * 查询失物推送列表
     */
    @RequiresPermissions("wx:push:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LafPush lafPush)
    {
        startPage();
        List<LafPush> list = lafPushService.selectLafPushList(lafPush);
        return getDataTable(list);
    }

    /**
     * 导出失物推送列表
     */
    @RequiresPermissions("wx:push:export")
    @Log(title = "失物推送", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LafPush lafPush)
    {
        List<LafPush> list = lafPushService.selectLafPushList(lafPush);
        ExcelUtil<LafPush> util = new ExcelUtil<LafPush>(LafPush.class);
        return util.exportExcel(list, "失物推送数据");
    }

    /**
     * 新增失物推送
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存失物推送
     */
    @RequiresPermissions("wx:push:add")
    @Log(title = "失物推送", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LafPush lafPush)
    {
        return toAjax(lafPushService.insertLafPush(lafPush));
    }

    /**
     * 修改失物推送
     */
    @GetMapping("/edit/{pushId}")
    public String edit(@PathVariable("pushId") Long pushId, ModelMap mmap)
    {
        LafPush lafPush = lafPushService.selectLafPushByPushId(pushId);
        mmap.put("lafPush", lafPush);
        return prefix + "/edit";
    }

    /**
     * 修改保存失物推送
     */
    @RequiresPermissions("wx:push:edit")
    @Log(title = "失物推送", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LafPush lafPush)
    {
        return toAjax(lafPushService.updateLafPush(lafPush));
    }

    /**
     * 删除失物推送
     */
    @RequiresPermissions("wx:push:remove")
    @Log(title = "失物推送", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(lafPushService.deleteLafPushByPushIds(ids));
    }
}
