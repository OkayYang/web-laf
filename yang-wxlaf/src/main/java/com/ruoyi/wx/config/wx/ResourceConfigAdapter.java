package com.ruoyi.wx.config.wx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 数据库图片地址到本机映射
 */
@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {
    @Value("${wx.upload.path}")
    private String imagePath;

    @Value("${cos.keyName}")
    private String cosPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        WebMvcConfigurer.super.addResourceHandlers(registry);
        //registry.addResourceHandler("/excel/**").addResourceLocations("file:E:\\excel\\");

        //虚拟路径映射
        String os = System.getProperty("os.name");

        if (os.toLowerCase().startsWith("win")) {
            System.out.println("当前运行环境是" + os);
            //win路径需要转义符
            registry.addResourceHandler(this.cosPath+"**").addResourceLocations("file:"+this.imagePath);
        }else{//linux系统
            System.out.println("当前运行环境是" + os);
            registry.addResourceHandler(this.cosPath+"**").addResourceLocations("file:"+this.imagePath);
            /*registry.addResourceHandler("/excel/**").addResourceLocations("file:/usr/asset/lou/excel/");*/
        }

    }
}

