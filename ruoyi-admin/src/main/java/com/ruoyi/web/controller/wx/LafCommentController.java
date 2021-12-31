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
import com.ruoyi.wx.domain.LafComment;
import com.ruoyi.wx.service.ILafCommentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 帖子留言Controller
 *
 * @author yang
 * @date 2021-12-26
 */
@Controller
@RequestMapping("/wx/comment")
public class LafCommentController extends BaseController
{
    private String prefix = "wx/comment";

    @Autowired
    private ILafCommentService lafCommentService;

    @RequiresPermissions("wx:comment:view")
    @GetMapping()
    public String comment()
    {
        return prefix + "/comment";
    }

    /**
     * 查询帖子留言树列表
     */
    @RequiresPermissions("wx:comment:list")
    @PostMapping("/list")
    @ResponseBody
    public List<LafComment> list(LafComment lafComment)
    {
        List<LafComment> list = lafCommentService.selectLafCommentList(lafComment);
        return list;
    }

    /**
     * 导出帖子留言列表
     */
    @RequiresPermissions("wx:comment:export")
    @Log(title = "帖子留言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LafComment lafComment)
    {
        List<LafComment> list = lafCommentService.selectLafCommentList(lafComment);
        ExcelUtil<LafComment> util = new ExcelUtil<LafComment>(LafComment.class);
        return util.exportExcel(list, "帖子留言数据");
    }

    /**
     * 新增帖子留言
     */
    @GetMapping(value = { "/add/{comId}", "/add/" })
    public String add(@PathVariable(value = "comId", required = false) Long comId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(comId))
        {
            mmap.put("lafComment", lafCommentService.selectLafCommentByComId(comId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存帖子留言
     */
    @RequiresPermissions("wx:comment:add")
    @Log(title = "帖子留言", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LafComment lafComment)
    {
        return toAjax(lafCommentService.insertLafComment(lafComment));
    }

    /**
     * 修改帖子留言
     */
    @GetMapping("/edit/{comId}")
    public String edit(@PathVariable("comId") Long comId, ModelMap mmap)
    {
        LafComment lafComment = lafCommentService.selectLafCommentByComId(comId);
        mmap.put("lafComment", lafComment);
        return prefix + "/edit";
    }

    /**
     * 修改保存帖子留言
     */
    @RequiresPermissions("wx:comment:edit")
    @Log(title = "帖子留言", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LafComment lafComment)
    {
        return toAjax(lafCommentService.updateLafComment(lafComment));
    }

    /**
     * 删除
     */
    @RequiresPermissions("wx:comment:remove")
    @Log(title = "帖子留言", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{comId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("comId") Long comId)
    {
        return toAjax(lafCommentService.deleteLafCommentByComId(comId));
    }

    /**
     * 选择帖子留言树
     */
    @GetMapping(value = { "/selectCommentTree/{comId}", "/selectCommentTree/" })
    public String selectCommentTree(@PathVariable(value = "comId", required = false) Long comId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(comId))
        {
            mmap.put("lafComment", lafCommentService.selectLafCommentByComId(comId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载帖子留言树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = lafCommentService.selectLafCommentTree();
        return ztrees;
    }
}