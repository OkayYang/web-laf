package com.ruoyi.web.controller.wx;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.domain.LafStudent;
import com.ruoyi.wx.service.ILafStudentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生Controller
 * 
 * @author yang
 * @date 2021-10-08
 */
@Controller
@RequestMapping("/wx/student")
public class LafStudentController extends BaseController
{
    private String prefix = "wx/student";

    @Autowired
    private ILafStudentService lafStudentService;

    @RequiresPermissions("wx:student:view")
    @GetMapping()
    public String student()
    {
        return prefix + "/student";
    }

    /**
     * 查询学生列表
     */
    @RequiresPermissions("wx:student:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LafStudent lafStudent)
    {
        startPage();
        List<LafStudent> list = lafStudentService.selectLafStudentList(lafStudent);
        return getDataTable(list);
    }

    @GetMapping("/getImage")
    @ResponseBody
    public String getStuImage(@RequestParam Long createId){
        return  lafStudentService.selectLafStudentByStuId(createId).getStuImage();
    }

    /**
     * 导出学生列表
     */
    @RequiresPermissions("wx:student:export")
    @Log(title = "学生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LafStudent lafStudent)
    {
        List<LafStudent> list = lafStudentService.selectLafStudentList(lafStudent);
        ExcelUtil<LafStudent> util = new ExcelUtil<LafStudent>(LafStudent.class);
        return util.exportExcel(list, "学生数据");
    }

    /**
     * 新增学生
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生
     */
    @RequiresPermissions("wx:student:add")
    @Log(title = "学生", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LafStudent lafStudent)
    {
        return toAjax(lafStudentService.insertLafStudent(lafStudent));
    }

    /**
     * 修改学生
     */
    @GetMapping("/edit/{stuId}")
    public String edit(@PathVariable("stuId") Long stuId, ModelMap mmap)
    {
        LafStudent lafStudent = lafStudentService.selectLafStudentByStuId(stuId);
        mmap.put("lafStudent", lafStudent);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生
     */
    @RequiresPermissions("wx:student:edit")
    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LafStudent lafStudent)
    {
        return toAjax(lafStudentService.updateLafStudent(lafStudent));
    }

    /**
     * 删除学生
     */
    @RequiresPermissions("wx:student:remove")
    @Log(title = "学生", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(lafStudentService.deleteLafStudentByStuIds(ids));
    }
}
