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
import com.ruoyi.wx.domain.LafCategory;
import com.ruoyi.wx.service.ILafCategoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 物品种类Controller
 * 
 * @author yang
 * @date 2021-10-08
 */
@Controller
@RequestMapping("/wx/category")
public class LafCategoryController extends BaseController
{
    private String prefix = "wx/category";

    @Autowired
    private ILafCategoryService lafCategoryService;

    @RequiresPermissions("wx:category:view")
    @GetMapping()
    public String category()
    {
        return prefix + "/category";
    }

    /**
     * 查询物品种类树列表
     */
    @RequiresPermissions("wx:category:list")
    @PostMapping("/list")
    @ResponseBody
    public List<LafCategory> list(LafCategory lafCategory)
    {
        List<LafCategory> list = lafCategoryService.selectLafCategoryList(lafCategory);
        return list;
    }

    /**
     * 导出物品种类列表
     */
    @RequiresPermissions("wx:category:export")
    @Log(title = "物品种类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LafCategory lafCategory)
    {
        List<LafCategory> list = lafCategoryService.selectLafCategoryList(lafCategory);
        ExcelUtil<LafCategory> util = new ExcelUtil<LafCategory>(LafCategory.class);
        return util.exportExcel(list, "物品种类数据");
    }

    /**
     * 新增物品种类
     */
    @GetMapping(value = { "/add/{cateId}", "/add/" })
    public String add(@PathVariable(value = "cateId", required = false) Long cateId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(cateId))
        {
            mmap.put("lafCategory", lafCategoryService.selectLafCategoryByCateId(cateId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存物品种类
     */
    @RequiresPermissions("wx:category:add")
    @Log(title = "物品种类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LafCategory lafCategory)
    {
        return toAjax(lafCategoryService.insertLafCategory(lafCategory));
    }

    /**
     * 修改物品种类
     */
    @GetMapping("/edit/{cateId}")
    public String edit(@PathVariable("cateId") Long cateId, ModelMap mmap)
    {
        LafCategory lafCategory = lafCategoryService.selectLafCategoryByCateId(cateId);
        mmap.put("lafCategory", lafCategory);
        return prefix + "/edit";
    }

    /**
     * 修改保存物品种类
     */
    @RequiresPermissions("wx:category:edit")
    @Log(title = "物品种类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LafCategory lafCategory)
    {
        return toAjax(lafCategoryService.updateLafCategory(lafCategory));
    }

    /**
     * 删除
     */
    @RequiresPermissions("wx:category:remove")
    @Log(title = "物品种类", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{cateId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("cateId") Long cateId)
    {
        return toAjax(lafCategoryService.deleteLafCategoryByCateId(cateId));
    }

    /**
     * 选择物品种类树
     */
    @GetMapping(value = { "/selectCategoryTree/{cateId}", "/selectCategoryTree/" })
    public String selectCategoryTree(@PathVariable(value = "cateId", required = false) Long cateId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(cateId))
        {
            mmap.put("lafCategory", lafCategoryService.selectLafCategoryByCateId(cateId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载物品种类树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = lafCategoryService.selectLafCategoryTree();
        return ztrees;
    }
}
