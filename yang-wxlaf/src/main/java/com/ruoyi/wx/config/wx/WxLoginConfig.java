package com.ruoyi.wx.config.wx;

import com.ruoyi.wx.config.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置过滤请求
 */
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
