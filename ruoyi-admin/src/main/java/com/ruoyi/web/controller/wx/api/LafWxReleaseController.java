package com.ruoyi.web.controller.wx.api;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.wx.domain.LafWxRelease;
import com.ruoyi.wx.service.BaiduService;
import com.ruoyi.wx.service.TencentService;
import com.ruoyi.wx.util.ImageUtil;
import com.ruoyi.wx.util.bean.wx.WxOrcIDCardResult;
import com.ruoyi.wx.util.bean.wx.WxRespResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.service.ILafReleaseService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/wx/api/release")
public class LafWxReleaseController extends WxBaseController {

    @Value("${wx.upload.path}")
    private String imagePath;
    @Value("${wx.api.host}")
    private String apiHost;
    @Value("${cos.keyName}")
    private String cosPath;


    @Autowired
    private ILafReleaseService lafReleaseService;

    @Autowired
    private BaiduService baiduService;

    @Autowired
    private TencentService tencentService;
    /**
     * wx 发布帖子接口
     * @param lafRelease
     * @return
     */

    @PostMapping("/auth/add/check")
    @ResponseBody
    @RepeatSubmit(interval = 500, message = "请求过于频繁")
    public WxRespResult addCheck(@RequestBody LafRelease lafRelease)
    {
        lafRelease.setCreateId(getWxUid());


        if (lafRelease.getRelImage() == null) {

            if ("1".equals(lafRelease.getRelStatus())) {
                lafRelease.setRelImage("/img/user/shiwu.png");
            }
            if ("2".equals(lafRelease.getRelStatus())) {
                lafRelease.setRelImage("/img/user/xunwu.png");
            }
        }
        String text = lafRelease.getRelTitle()+lafRelease.getRelDesc()+lafRelease.
                getCreatePlace()+lafRelease.getRelCampus()+lafRelease.getRelContact();

        if (tencentService.checkText(text,getOpenid(),3)==100) {
            return toAjax(lafReleaseService.insertLafRelease(lafRelease));
        }else {
            return WxRespResult.sensitive();
        }


    }
    /**
     * wx  发布图片接口
     * @param file
     * @return   返回帖子图片地址
     * @throws Exception
     */

    @ResponseBody
    @PostMapping("/upload")
    @RepeatSubmit(interval = 500, message = "请求过于频繁")
    public WxRespResult upload(@RequestParam("file") MultipartFile file ) throws Exception{
        WxOrcIDCardResult wxOrcIDCardResult = new WxOrcIDCardResult();
        if (file==null){
            return error("上传文件不合法!");
        }
        //fileName是你前台传参时的文件名字，也可以不指定
        //不指定名字，保存时使用 file.getOriginalFilename()得到文件名字

        //String fileName = file.getOriginalFilename();
        String fileName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf(".")) + ".jpg";
        //图片地址
        String imageUri = cosPath+fileName;
        //网络请求图片资源地址
        String imageUrl = this.apiHost+imageUri;
        //本机图片存储地址
        String imagePath = this.imagePath+fileName;

        //保存到本地
        File file1 = new File(imagePath);
        file.transferTo(file1);

        //图片压缩
        ImageUtil.reduce(file1);

        if (tencentService.checkMedia(imageUrl,getOpenid(),3)!=100){
            return WxRespResult.sensitive();
        }

        wxOrcIDCardResult.setPhotoUrl(imageUri);
        //ocr识别打码
        baiduService.classRecognize(wxOrcIDCardResult,imageUrl,imagePath);

        //保存到文件服务器，OSS服务器
        tencentService.ContentCOS(file1,getRequest(),getResponse());

        //返回ocr识别结果
        return success(wxOrcIDCardResult);
    }

    @PostMapping("/list")
    @ResponseBody
    public PageInfo<LafWxRelease> list(@RequestBody LafWxRelease lafWxRelease, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);

        List<LafWxRelease> list = lafReleaseService.selectLafWxReleaseList(lafWxRelease);
        PageInfo<LafWxRelease> pageInfo = new PageInfo<LafWxRelease>(list,pageSize);
        return pageInfo;
    }


    /**
     * wx 查询个人发布
     */
    @ResponseBody
    @GetMapping("/auth/publish")
    public TableDataInfo myRelease(@Param("id")String id){
        startPage();
        LafWxRelease lafWxRelease = new LafWxRelease();

        if ("1".equals(id)||"2".equals(id)){
            lafWxRelease.setStuId(getWxUid());
            lafWxRelease.setRelStatus(id);
        }
        if("3".equals(id)){
            lafWxRelease.setStuId(getWxUid());
            lafWxRelease.setRelFlag("2");
        }
        if ("4".equals(id)){
            lafWxRelease.setRelClaimId(getWxUid());
        }
        List<LafWxRelease> list = lafReleaseService.selectLafWxReleaseList(lafWxRelease);
        return getDataTable(list);
    }
    /**
     * 微信用户删除
     * @param tid
     * @return
     */

    @Log(title = "帖子", businessType = BusinessType.DELETE)
    @GetMapping( "/auth/delete")
    @ResponseBody
    @RepeatSubmit(interval = 500, message = "请求过于频繁")
    public WxRespResult remove(@RequestParam("id") Long tid)
    {
        LafRelease release = new LafRelease();
        release.setCreateId(getWxUid());
        release.setRelId(tid);
        List<LafRelease> list  = lafReleaseService.selectLafReleaseList(release);
        if (list.size()>0){
            release.setRelDel("2");
            return toAjax(lafReleaseService.updateLafRelease(release));
        }else {
            return error("删除失败");
        }

    }

    /**
     * 微信用户删除
     */
    @Log(title = "帖子", businessType = BusinessType.UPDATE)
    @PostMapping("/auth/edit")
    @ResponseBody
    public WxRespResult editSave(@RequestBody LafRelease lafRelease)
    {
        LafRelease release = new LafRelease();
        release.setCreateId(getWxUid());
        release.setRelId(lafRelease.getRelId());
        List<LafRelease> list  = lafReleaseService.selectLafReleaseList(release);
        if (list.size()>0){
            return toAjax(lafReleaseService.updateLafRelease(lafRelease));
        }else {
            return error("修改失败");
        }

    }

    @GetMapping("/skim")
    @ResponseBody
    public WxRespResult updateSkim(@RequestParam("tid") Long tid){
        return toAjax(lafReleaseService.updateBrowse(tid));
    }


}
