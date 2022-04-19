package com.ruoyi.web.controller.wx.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.wx.util.bean.wx.WxRespResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.wx.util.token.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

public class WxBaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 通过请求头token获取用户唯一表示id
     * @return uid
     * @Author yang
     */
    public Long getWxUid(){
        Long wxUid = null;
        try{
            wxUid= JwtUtils.getAppUID(getRequest().getHeader("token"));
        }catch (Exception e){
            e.printStackTrace();
        }

        return wxUid;
    }
    /**
     * 获取request
     */
    public HttpServletRequest getRequest()
    {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse()
    {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession()
    {
        return getRequest().getSession();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected WxRespResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     *
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected WxRespResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public WxRespResult success()
    {
        return WxRespResult.success();
    }

    /**
     * 返回失败消息
     */
    public WxRespResult error()
    {
        return WxRespResult.error();
    }

    /**
     * 返回成功消息
     */
    public WxRespResult success(String message)
    {
        return WxRespResult.success(message);
    }

    /**
     * 返回成功数据
     */
    public static WxRespResult success(Object data)
    {
        return WxRespResult.success("操作成功", data);
    }

    /**
     * 返回成功数据
     *
     * @Author yang
     */
    public static WxRespResult success(Object data, String token)

    {
        return WxRespResult.success("操作成功", data,token);
    }

    /**
     * 返回失败消息
     */
    public WxRespResult error(String message)
    {
        return WxRespResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public WxRespResult error(WxRespResult.Type type, String message)
    {
        return new WxRespResult(type, message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息
     */
    public SysUser getSysUser()
    {
        return ShiroUtils.getSysUser();
    }

    /**
     * 设置用户缓存信息
     */
    public void setSysUser(SysUser user)
    {
        ShiroUtils.setSysUser(user);
    }

    /**
     * 获取登录用户id
     */
    public Long getUserId()
    {
        return getSysUser().getUserId();
    }

    /**
     * 获取登录用户名
     */
    public String getLoginName()
    {
        return getSysUser().getLoginName();
    }
}
