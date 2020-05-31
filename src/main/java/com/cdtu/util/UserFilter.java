package com.cdtu.util;

import com.cdtu.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 拦截器
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
        , urlPatterns = { "*.jsp" })
public class UserFilter implements Filter {

    private static final List<String> WIHTELIST = Arrays.asList("/user/login","/user/resetPassword","/user/sendSms");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        //获取访问的地址
        String url = req.getRequestURI();
        //先判断是不是在URL白名单中，如果在白名单中则直接放行不需要检查session
        //否则判断Session中有无user这个属性
        //如果没有则重定向到登录页面
        if(!WIHTELIST.contains(url) && null == req.getSession().getAttribute("user")) {
            if(url.equals("/user/login")){
                resp.sendRedirect("/user/login");
                return;
            }

        }
        //继续执行过滤连的剩余部分
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
