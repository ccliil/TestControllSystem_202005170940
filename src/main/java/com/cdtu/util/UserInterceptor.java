package com.cdtu.util;

import com.cdtu.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 过滤器
 */
public class UserInterceptor implements HandlerInterceptor {
    private static  final List<String> IGNORE_URI= Arrays.asList("/login","/loginForm","/404.html","/user/sendSms","/user/resetPassword");//定义不用拦截的请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag=false;
        String servletPath=request.getServletPath();
        flag = IGNORE_URI.contains(servletPath);
        System.out.println(servletPath);

        if(!flag){
            User user= (User) request.getSession().getAttribute(HrmConstants.USER_SESSION);
            if(user==null){
                request.setAttribute("message","请先登录再访问网站！");
                request.getRequestDispatcher("/user/login").forward(request,response);
                return flag;
            }else {
                flag=true;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
