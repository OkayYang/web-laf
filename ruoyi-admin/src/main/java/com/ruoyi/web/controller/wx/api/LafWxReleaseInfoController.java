package com.ruoyi.web.controller.wx.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.wx.domain.LafComment;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.domain.LafWxRelease;
import com.ruoyi.wx.service.ILafCommentService;
import com.ruoyi.wx.service.TencentService;
import com.ruoyi.wx.util.bean.wx.WxRespResult;
import com.ruoyi.wx.util.bean.wx.commentBean.CommentDetail;
import com.ruoyi.wx.util.bean.wx.commentBean.CommentTree;
import com.ruoyi.wx.service.ILafReleaseService;
import com.ruoyi.wx.service.ILafStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Api("查看发布详细信息")
@Controller
@RequestMapping("/wx/api/info")
public class LafWxReleaseInfoController extends WxBaseController {

    @Value("${wx.upload.path}")
    private String imagePath;

    @Value("${cos.keyName}")
    private String cosPath;



    @Autowired
    private ILafReleaseService lafReleaseService;
    @Autowired
    private ILafStudentService lafStudentService;
    @Autowired
    private ILafCommentService lafCommentService;

    @Autowired
    private TencentService tencentService;
    /**
     * 查询帖子详细信息
     * @param relId
     * @return 该帖子详细信息，发布者和内容
     */
    @ApiOperation("查看选中物品详细信息")
    @ApiImplicitParam(name = "relId", value = "选中物品ID", required = true, dataType = "int")
    @GetMapping("/list")
    @ResponseBody
    public LafWxRelease selectList(@RequestParam Long relId)
    {
        return lafReleaseService.selectLafWxReleaseByRelId(relId);

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

    @GetMapping("/test")
    @ResponseBody
    @Transactional
    public List<CommentTree> test(@RequestParam Long relId)
    {
        List<CommentTree> commentTree = lafCommentService.testComment(relId);
        return commentTree;
    }


    /**
     * 新增当前帖子留言
     */
//    @Log(title = "帖子留言", businessType = BusinessType.INSERT)
//    @RequestMapping ("/auth/comment/add")
//    @ResponseBody
//    public WxRespResult addSave(@RequestParam(value = "pid",required=false) Long pid,@RequestParam("tid") Long tid,@RequestParam("content") String content) throws IOException {
//
//        LafComment lafComment = new LafComment();
//        lafComment.setParentId(pid);
//        lafComment.setComRelId(tid);
//        lafComment.setComStuId(getWxUid());
//        lafComment.setComContent(content);
//        return toAjax(lafCommentService.insertLafComment(lafComment));
//    }
    /**
     * 新增当前帖子留言
     */
    @Log(title = "帖子留言", businessType = BusinessType.INSERT)
    @PostMapping ("/auth/comment/add")
    @ResponseBody
    public WxRespResult addSave(@RequestBody LafComment lafComment) throws IOException {

        LafComment comment = new LafComment();
        comment.setParentId(lafComment.getParentId());
        comment.setComRelId(lafComment.getComRelId());
        comment.setComStuId(getWxUid());
        comment.setComContent(lafComment.getComContent());
        comment.setComImage(lafComment.getComImage());
        return toAjax(lafCommentService.insertLafComment(comment));
    }
    /**
     * wx  上传接口
     * @param file
     * @return   返回帖子图片地址
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file ) throws Exception{
        if (file!=null){
            //fileName是你前台传参时的文件名字，也可以不指定
            String fileName = file.getOriginalFilename();
            String imageUri = cosPath+fileName;
            String imagePath = this.imagePath+fileName;
            //不指定名字，保存时使用 file.getOriginalFilename()得到文件名字
            //保存到文件到本地
            File file1 = new File(imagePath);
            file.transferTo(file1);

            //保存到文件服务器，OSS服务器
            tencentService.ContentCOS(file1,getRequest(),getResponse());
            return imageUri;
        }else {
            return "请上传图片!!!";
        }

    }


    /**
     * wx用户删除评论
     */

    @Log(title = "帖子留言", businessType = BusinessType.DELETE)
    @GetMapping("/auth/comment/delete")
    @ResponseBody
    public WxRespResult remove(@RequestParam("id") Long comId)
    {

        LafComment lafComment = new LafComment();
        lafComment.setComId(comId);
        lafComment.setComStuId(getWxUid());
        List<LafComment> list = lafCommentService.selectLafCommentList(lafComment);
        if (list.size()>0){
            return toAjax(lafCommentService.deleteLafCommentByComId(comId));
        }
        return error("删除失败");
    }

    @GetMapping("/auth/claim")
    @ResponseBody
    public WxRespResult claim(@RequestParam Long relId){
        LafRelease lafRelease = lafReleaseService.selectLafReleaseByRelId(relId);
        if (lafRelease.getRelClaimId()==null){
            lafRelease.setRelId(relId);
            lafRelease.setRelFlag("2");
            lafRelease.setRelTime(DateUtils.getNowDate());
            lafRelease.setRelClaimId(getWxUid());
            return toAjax(lafReleaseService.updateLafRelease(lafRelease));

        }else {
            return error("已经被认领!!");
        }
    }
    @GetMapping("/auth/unClaim")
    @ResponseBody
    public WxRespResult cancelClaim(@RequestParam Long relId){
        LafRelease lafRelease = lafReleaseService.selectLafReleaseByRelId(relId);
        if (lafRelease.getRelClaimId()!=null){
            lafRelease = new LafRelease();
            lafRelease.setRelId(relId);
            lafRelease.setRelFlag("1");
            return toAjax(lafReleaseService.updateLafRelease(lafRelease));

        }else {
            return error("该物品没人认领!!");
        }

    }

}
