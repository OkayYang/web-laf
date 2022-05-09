package com.ruoyi.web.controller.wx.api;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.wx.service.TencentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wx/api/file")
public class LafWxFileController extends BaseController {
    @Autowired
    private TencentService tencentService;
    @Autowired
    private ServerConfig serverConfig;

    private static final String FILE_DELIMETER = ",";
    /**
     * wx通用上传请求（单个）
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult wxUploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            Map<String,Object> map = FileUploadUtils.wxUpload(filePath, file);
            String fileName = (String) map.get("fileName");
            File fileDesc = (File) map.get("fileDesc");
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            tencentService.ContentCOS(fileDesc,getRequest(),getResponse());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    @ResponseBody
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            List<String> fileNames = new ArrayList<String>();
            List<String> urls = new ArrayList<String>();
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
                // 上传并返回新文件名称
                Map<String,Object> map = FileUploadUtils.wxUpload(filePath, file);
                String fileName = (String) map.get("fileName");
                File fileDesc = (File) map.get("fileDesc");
                String url = serverConfig.getUrl() + fileName;
                fileNames.add(fileName);
                tencentService.ContentCOS(fileDesc,getRequest(),getResponse());
                urls.add(url);
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
}
