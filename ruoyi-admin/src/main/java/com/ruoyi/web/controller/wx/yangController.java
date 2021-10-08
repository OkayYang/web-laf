package com.ruoyi.web.controller.wx;

import com.ruoyi.common.core.controller.WxBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wx/yang")
public class yangController extends WxBaseController {
    @GetMapping("/test")
    @ResponseBody
    public String test(){

        System.out.println(getWxUid());
        return "hello world";
    }


}
