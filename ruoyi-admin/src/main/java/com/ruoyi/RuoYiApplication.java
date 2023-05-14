package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  失物招领系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "    __            ___            ______\n" +
                "   / /           /   |          / ____/\n" +
                "  / /           / /| |         / /_    \n" +
                " / /___        / ___ |        / __/    \n" +
                "/_____/       /_/  |_|       /_/       ");
    }
}