package com.ruoyi.web.controller.wx.api;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.wx.domain.LafArticle;
import com.ruoyi.wx.service.ILafArticleService;
import com.ruoyi.wx.util.bean.wx.WxRespResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/wx/api/article")
@Controller
public class LafWxArticleController extends WxBaseController{
    @Autowired
    private ILafArticleService lafArticleService;

    /**
     * 查询article列表
     */
    @GetMapping("/list")
    @ResponseBody
    public List<LafArticle> list(LafArticle lafArticle)
    {
        return lafArticleService.selectLafArticleList(lafArticle);

    }
    /**
     * 查询article列表
     */
    @GetMapping("/week")
    @ResponseBody
    public WxRespResult week(@RequestParam(value = "id",required = false) Long id)
    {
        LafArticle lafArticle = new LafArticle();
        if (id!=null){
            lafArticle = lafArticleService.selectLafArticleById(id);
        }else {
            List<LafArticle> list = lafArticleService.selectLafArticleList(lafArticle);
            if (list.size()>0){
                lafArticle = list.get(0);
            }else {
                return error();
            }
        }
        return success(lafArticle);

    }

    /**
     * 查询article列表
     */
    @GetMapping("/select")
    @ResponseBody
    public WxRespResult selectWeek(@RequestParam("id") Long id)
    {
        LafArticle lafArticle = lafArticleService.selectLafArticleById(id);
        if (lafArticle!=null){
            return success(lafArticle);
        }else {
            return error();
        }
    }
    

}
