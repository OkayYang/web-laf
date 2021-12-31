package com.ruoyi.framework.interceptor.wx;

import com.ruoyi.common.core.domain.WxLoginResult;
import com.ruoyi.common.utils.wx.JwtUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor  {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long wxUid = null;
        boolean flag = false;
        try{
            wxUid= JwtUtils.getAppUID(request.getHeader("token"));
            if (wxUid!=null){
                flag = true;
            }else {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"error\":\"未登录!\"}");
                response.getWriter().flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
