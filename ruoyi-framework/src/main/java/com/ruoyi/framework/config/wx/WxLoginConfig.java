package com.ruoyi.framework.config.wx;

import com.ruoyi.framework.interceptor.wx.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WxLoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //配置需要拦截的
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(
                        "/wx/api/*/auth/**"


                );
    }
}
