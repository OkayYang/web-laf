package com.ruoyi.web.controller.wx.api;


import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.wx.service.ILafCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/wx/api/category")
public class LafWxCategoryController {

    @Autowired
    private ILafCategoryService lafCategoryService;

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
