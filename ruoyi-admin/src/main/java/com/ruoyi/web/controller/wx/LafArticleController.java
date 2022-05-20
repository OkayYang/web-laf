package com.ruoyi.wx.controller;

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
import com.ruoyi.wx.domain.LafArticle;
import com.ruoyi.wx.service.ILafArticleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * articleController
 * 
 * @author yang
 * @date 2022-05-09
 */
@Controller
@RequestMapping("/wx/article")
public class LafArticleController extends BaseController
{
    private String prefix = "wx/article";

    @Autowired
    private ILafArticleService lafArticleService;

    @RequiresPermissions("wx:article:view")
    @GetMapping()
    public String article()
    {
        return prefix + "/article";
    }

    /**
     * 查询article列表
     */
    @RequiresPermissions("wx:article:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LafArticle lafArticle)
    {
        startPage();
        List<LafArticle> list = lafArticleService.selectLafArticleList(lafArticle);
        return getDataTable(list);
    }

    /**
     * 导出article列表
     */
    @RequiresPermissions("wx:article:export")
    @Log(title = "article", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LafArticle lafArticle)
    {
        List<LafArticle> list = lafArticleService.selectLafArticleList(lafArticle);
        ExcelUtil<LafArticle> util = new ExcelUtil<LafArticle>(LafArticle.class);
        return util.exportExcel(list, "article数据");
    }

    /**
     * 新增article
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存article
     */
    @RequiresPermissions("wx:article:add")
    @Log(title = "article", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LafArticle lafArticle)
    {
        return toAjax(lafArticleService.insertLafArticle(lafArticle));
    }

    /**
     * 修改article
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        LafArticle lafArticle = lafArticleService.selectLafArticleById(id);
        mmap.put("lafArticle", lafArticle);
        return prefix + "/edit";
    }

    /**
     * 修改保存article
     */
    @RequiresPermissions("wx:article:edit")
    @Log(title = "article", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LafArticle lafArticle)
    {
        return toAjax(lafArticleService.updateLafArticle(lafArticle));
    }

    /**
     * 删除article
     */
    @RequiresPermissions("wx:article:remove")
    @Log(title = "article", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(lafArticleService.deleteLafArticleByIds(ids));
    }
}
