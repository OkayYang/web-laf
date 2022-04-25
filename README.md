##江理失物招领系统（后台管理系统+微信小程用户界面）
###1.前言
在我校校园，没有一个相对安全的发布平台。只有一些校园失物招领群，校园表白墙可以进行投稿，信息获取面比较窄小，也不易于失物的找回，同时浪费多人的时间，失物信息的发布只能保持一定量的时间，会被新的信息所覆盖，相当于发布的失物消息没有多少人可以看到，过于局限性，起不到一个特别好的作用。运用当今流行微信小程序，开发一个失物招领平台可以形成良好的用户体验。因此和几个同学结合大学期间所学专业知识开发了一个本校的失物招领系统，普通用户可以通过微信小程序进行失物招领发布认领等功能。后台管理员可以通过后台管理系统对数据分析查看。<br>
本系统后台管理基于优秀的开源项目Ruoyi，前端采用微信小程序原生开发+uniapp混合式开发，关于若依本文不再介绍，如需了解请移步[http://doc.ruoyi.vip/ruoyi/](http://doc.ruoyi.vip/ruoyi/)

###2.涉及技术
1.	RuoYi后台管理框架（Spring Boot、Apache Shiro、MyBatis、Thymeleaf）
2.	JSON Web Token（微信用户登陆后发放，用于后期通讯携带标识）
3.	百度OCR通用文字识别，卡证识别api。
4.	微信小程序用户登陆api，微信小程序模板消息通知api
5.	定时器任务（定时请求api刷新百度Token和模板消息Token）
6.	微信小程序原生语言和uniapp混合式开发（第三方组件库[Vant](https://vant-contrib.gitee.io/vant-weapp/#/home)+[uView](https://www.uviewui.com/components/intro.html)）
7.  nginx反向代理

###3.环境部署
#####3.1准备工作
JDK >= 1.8 (推荐1.8版本)
 Mysql >= 5.7.0 (推荐5.7版本) 
Maven >= 3.0
#####3.2运行后台系统
3.21	导入项目到Eclipse或IDEA等待加载Maven依赖包
3.22	创建数据库laf 将sql文件中数据脚本导入
3.23	必要配置
- 修改数据库连接，编辑resources目录下的application-druid.yml
- 修改服务器配置，编辑resources目录下的application.yml
- 修改资源配置，编辑辑resources目录下的application-druid.yml

3.24打开项目运行com.ruoyi.RuoYiApplication.java，出现如启动成功。
~~~
 (♥◠‿◠)ﾉﾞ  失物招领系统启动成功   ლ(´ڡ`ლ)ﾞ  
    __            ___            ______
   / /           /   |          / ____/
  / /           / /| |         / /_    
 / /___        / ___ |        / __/    
/_____/       /_/  |_|       /_/    
~~~
3.25  打开浏览器，输入：(http://localhost:80) （默认账户/密码 admin/yang611612）若能正确展示登录页面，并能成功登录，菜单及页面展示正常，则表明环境搭建成功。


###4.项目目录介绍
主目录结构仅列出各个模块，其中yang-wxlaf模块和ruoyi-admin模块下的com.ruoyi.web.controller.wx包和com.ruoyi.web.controller.wx.api(微信请求api接口)包为本系统的主要代码。
~~~
web-laf 
├─ruoyi-admin        //后台服务处理请求                   
├─ruoyi-common       //工具类                     
├─ruoyi-framework    //核心框架                            
├─ruoyi-generator    //代码生成                   
├─ruoyi-quartz       //定时器                   
├─ruoyi-system       //ruoyi系统代码                 
└─yang-wxlaf   	     //失物招领系统代码
~~~
    
 

对yang-laf模块详细介绍

~~~
    yang-wxlaf
    │  pom.xml //所需的依赖包
    └─src
        └─main
            ├─java
            │  └─com
            │      └─ruoyi
            │          └─wx
            │              ├─config    //配置文件
            │              │  ├─interceptor
            │              │  │      //微信用户认证过滤，防止非法请求,一些涉及权限需要携带token
            │              │  │      LoginInterceptor.java 
            │              │  │      
            │              │  └─wx
            │              │          //资源映射，将请求的资源映射到主机资源位置
            │              │          ResourceConfigAdapter.java
            │              │          //过滤请求，拦截需要认证的请 求，配合LoginInterceptor使用
            │              │          WxLoginConfig.java
            │              │          
            │              ├─domain     //实体类
            │              ├─mapper	//数据持久层
            │              ├─service    //业务逻辑层
            │              └─util       //一些工具包
            │                  │        
            │                  │  HttpUtil.java     //http请求工具类
            │                  ├─baidu              //百度ocr识别结果集和打工具
            │                  │  │  WaterMark.java          //图片打码
            │                  │  │  
            │                  │  └─domain          //处理百度ocr识别返回的字段
            │                  │          BaiduOcrSfzResult.java
            │                  │          ClassifierData.java
            │                  │          ClassifierRet.java
            │                  │          Location.java     //打码坐标区域
            │                  │          Unit.java
            │                  │          WordsResult.java
            │                  │          
            │                  ├─bean //系统需要的实体类
            │                  │  ├─echartBean      //echart对应的数据
            │                  │  │      Graph.java
            │                  │  │      Statistic.java
            │                  │  │      
            │                  │  └─wx          //小程序一些返回信息需要处理的类
            │                  │      │  User.java
            │                  │      │  WxOrcIDCardResult.java
            │                  │      │  WxRespResult.java
            │                  │      │  WxUserModel.java
            │                  │      │  
            │                  │      └─commentBean  //构建一个树状评论类
            │                  │              CommentDetail.java
            │                  │              CommentTree.java
            │                  │              
            │                  ├─tencent //微信api接口调用模板类（登陆请求）
            │                  │  │ 
            │                  │  └─bean    //微信消息订阅模板对应的实体
            │                  │          TemplateData.java
            │                  │          WxMessVo.java
            │                  │          
            │                  └─token      
            │                          AccessTokenTask.java     //定时任务获取百度和腾讯账户token
            │                          JwtUtils.java    //jwt签名生成分发解析
            │                          
            └─resources
                └─mapper  //对数据库进行操作  
~~~    

###5.后台手册
5.1  数据库设计

5.2 ruoyi系统的基础上失物招领模块新加功能
- 通过echart图标展示系统数据
- 通过不同校区管理员进行权限分配（开发中...）
- 添加定时器定时执行任务（如定时向腾讯，百度发送api请求获取最新token）
- 小程序程序公告通知管理功能，支持增删改查操作。
- 小程序物品类别管理功能，支持增删改查操作。
- 小程序发布管理功能，支持增删改查操作。
- 小程序用户管理功能，支持增删改查操作。
- 小程序评论管理功能，支持增删改查操作。
- 小程序用户推送功能，支持增删改查操作。

###6.微信小程序
微信小程序需要接口api可在[apiHost开放文档](https://console-docs.apipost.cn/preview/d80df754ab88d4de/fafb94f099eede1d)查看。
具体请移步小程序开发手册[点击前往](https://github.com/PGone-AKA/uniapp-laf#readme)


在线体验小程序


![](https://laf.4w3w.com/img/user/tiezi/xcxonline.jpg)
###7.其他




