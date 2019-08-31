package com.free.zdp.filter;


import com.free.zdp.config.MvcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器
 */
@WebFilter(filterName = "MyFilter",urlPatterns = "/*")
public class MyFilter implements Filter {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        Logger.info("【***********************FILTER*********************************】过滤到的请求--->"+requestURI);
        filterChain.doFilter(servletRequest,servletResponse);
//        Logger.info("【***********************FILTER*********************************】");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Logger.info("【***********************FILTER*********************************】初始化过滤器");
    }

    @Override
    public void destroy() {
        Logger.info("【***********************FILTER*********************************】关闭过滤器");
    }
}
