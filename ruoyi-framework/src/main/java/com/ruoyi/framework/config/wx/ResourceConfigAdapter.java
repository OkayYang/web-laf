package com.ruoyi.framework.config.wx;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        WebMvcConfigurer.super.addResourceHandlers(registry);
        //registry.addResourceHandler("/excel/**").addResourceLocations("file:E:\\excel\\");

        //虚拟路径映射
        String os = System.getProperty("os.name");

        if (os.toLowerCase().startsWith("win")) {
            System.out.println("当前运行环境是" + os);
            //win路径需要转义符
            registry.addResourceHandler("/img/user/tiezi/**").addResourceLocations("file:D:\\BaiduNetdiskWorkspace\\tiezi\\");
        }else{//linux系统
            System.out.println("当前运行环境是" + os);
            /*registry.addResourceHandler("/excel/**").addResourceLocations("file:/usr/asset/lou/excel/");*/
        }

    }
}

