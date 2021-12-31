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
import com.ruoyi.wx.domain.LafAnnounce;
import com.ruoyi.wx.service.ILafAnnounceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小程序公告Controller
 *
 * @author yang
 * @date 2021-12-26
 */
@Controller
@RequestMapping("/wx/announce")
public class LafAnnounceController extends BaseController
{
    private String prefix = "wx/announce";

    @Autowired
    private ILafAnnounceService lafAnnounceService;

    @RequiresPermissions("wx:announce:view")
    @GetMapping()
    public String announce()
    {
        return prefix + "/announce";
    }

    /**
     * 查询小程序公告列表
     */
    @RequiresPermissions("wx:announce:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LafAnnounce lafAnnounce)
    {
        startPage();
        List<LafAnnounce> list = lafAnnounceService.selectLafAnnounceList(lafAnnounce);
        return getDataTable(list);
    }

    /**
     * 导出小程序公告列表
     */
    @RequiresPermissions("wx:announce:export")
    @Log(title = "小程序公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LafAnnounce lafAnnounce)
    {
        List<LafAnnounce> list = lafAnnounceService.selectLafAnnounceList(lafAnnounce);
        ExcelUtil<LafAnnounce> util = new ExcelUtil<LafAnnounce>(LafAnnounce.class);
        return util.exportExcel(list, "小程序公告数据");
    }

    /**
     * 新增小程序公告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存小程序公告
     */
    @RequiresPermissions("wx:announce:add")
    @Log(title = "小程序公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LafAnnounce lafAnnounce)
    {
        return toAjax(lafAnnounceService.insertLafAnnounce(lafAnnounce));
    }

    /**
     * 修改小程序公告
     */
    @GetMapping("/edit/{annId}")
    public String edit(@PathVariable("annId") Long annId, ModelMap mmap)
    {
        LafAnnounce lafAnnounce = lafAnnounceService.selectLafAnnounceByAnnId(annId);
        mmap.put("lafAnnounce", lafAnnounce);
        return prefix + "/edit";
    }

    /**
     * 修改保存小程序公告
     */
    @RequiresPermissions("wx:announce:edit")
    @Log(title = "小程序公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LafAnnounce lafAnnounce)
    {
        return toAjax(lafAnnounceService.updateLafAnnounce(lafAnnounce));
    }

    /**
     * 删除小程序公告
     */
    @RequiresPermissions("wx:announce:remove")
    @Log(title = "小程序公告", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(lafAnnounceService.deleteLafAnnounceByAnnIds(ids));
    }
}