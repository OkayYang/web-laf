package com.ruoyi.web.controller.wx.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.WxLoginResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.domain.LafComment;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.service.ILafCommentService;
import com.ruoyi.wx.util.CommentDetail;
import com.ruoyi.wx.util.LafReleaseInfo;
import com.ruoyi.wx.domain.LafStudent;
import com.ruoyi.wx.service.ILafReleaseService;
import com.ruoyi.wx.service.ILafStudentService;
import com.ruoyi.wx.util.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("查看发布详细信息")
@Controller
@RequestMapping("/wx/api/info")
public class LafWxReleaseInfoController extends BaseController {
    @Autowired
    private ILafReleaseService lafReleaseService;
    @Autowired
    private ILafStudentService lafStudentService;
    @Autowired
    private ILafCommentService lafCommentService;
    /**
     * 查询帖子详细信息
     * @param relId
     * @return 该帖子详细信息，发布者和内容
     */
    @ApiOperation("查看选中物品详细信息")
    @ApiImplicitParam(name = "relId", value = "选中物品ID", required = true, dataType = "int")
    @GetMapping("/list")
    @ResponseBody
    @Transactional
    public LafReleaseInfo selectList(@RequestParam Long relId)
    {

        LafRelease lafRelease = lafReleaseService.selectLafReleaseByRelId(relId);

        if (lafRelease!=null){
            LafStudent lafStudent = lafStudentService.selectLafStudentByStuId(lafRelease.getCreateId());
            LafReleaseInfo lafReleaseInfo = new LafReleaseInfo();
            lafReleaseInfo.setLafRelease(lafRelease);
            User user = new User(lafStudent.getStuImage(),lafStudent.getStuNick(),lafStudent.getStuSex(),lafStudent.getStuId());
            lafReleaseInfo.setPublish_user(user);
            return lafReleaseInfo;
        }else return null;
    }
    @ApiOperation("查看选中物品评论")
    @ApiImplicitParam(name = "relId", value = "选中物品ID", required = true, dataType = "int")
    @GetMapping("/comments")
    @ResponseBody
    @Transactional
    public List<CommentDetail> selectComments(@RequestParam Long relId)
    {

        List<CommentDetail> commentDetailList = lafCommentService.selectLafCommentByTid(relId);
        return commentDetailList;
    }
    /**
     * 新增当前帖子留言
     */
    @Log(title = "帖子留言", businessType = BusinessType.INSERT)
    @PostMapping("/auth/comment/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody LafComment lafComment)
    {
        return toAjax(lafCommentService.insertLafComment(lafComment));
    }


    /**
     * wx用户删除评论
     */

    @Log(title = "帖子留言", businessType = BusinessType.DELETE)
    @GetMapping("/auth/comment/remove")
    @ResponseBody
    public AjaxResult remove(@RequestParam("comId") Long comId)
    {
        return toAjax(lafCommentService.deleteLafCommentByComId(comId));
    }

}
