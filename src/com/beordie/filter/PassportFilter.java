package com.beordie.filter;

import com.beordie.model.Admin;
import com.beordie.utils.AdminUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname PassportFilter
 * @Description 通行证
 * @Date 2021/6/29 19:43
 * @Created 30500
 */
public class PassportFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到请求");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().endsWith("/admin/login.html") || request.getRequestURI().endsWith("/admin/login.udo"))
            filterChain.doFilter(request, response);
        else if (AdminUtils.getAdminName(request.getSession()) != null)
            filterChain.doFilter(request, response);
        else
            response.sendRedirect("/admin/login.html");

    }

    @Override
    public void destroy() {

    }
}
