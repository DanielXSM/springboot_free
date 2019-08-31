package com.free.zdp.config;

import com.free.zdp.filter.MyFilter;
import com.free.zdp.interceptor.MyInterceptor1;
import com.free.zdp.interceptor.MyInterceptor2;
import com.free.zdp.listener.MyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private static final Logger Logger = LoggerFactory.getLogger(MvcConfig.class);
    //需要走拦截器的请求路径
    private static String urlPatterns1[]=new String[]{"/*"};

    //需要走拦截器的请求路径2
    private static String urlPatterns2[]=new String[]{"/*"};


    /**
     * 拦截器1
     * @return
     */
    @Bean
    MyInterceptor1 getMyInterceptor1(){
        return new MyInterceptor1();
    }

    /**
     * 拦截器2
     * @return
     */
    @Bean
    MyInterceptor2 getMyInterceptor2(){
        return new MyInterceptor2();
    }


    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor1()).addPathPatterns(urlPatterns1);//拦截器1
        registry.addInterceptor(getMyInterceptor2()).addPathPatterns(urlPatterns2);//拦截器2
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                Logger.debug("【IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII】前置方法被执行~~~~");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                Logger.debug("【IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII】后置方法被执行~~~~");
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                Logger.debug("【IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII】最终方法被执行~~~~");
            }
        });
    }

    /**
     * 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegister(){
        FilterRegistrationBean frBean=new FilterRegistrationBean();
        frBean.setFilter(new MyFilter());
        frBean.addUrlPatterns("/*");
        return frBean;

    }

    /**
     * 监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean listenerRegiste(){
        ServletListenerRegistrationBean srb=new ServletListenerRegistrationBean();
        srb.setListener(new MyListener());
        return srb;
    }
    /**
     * 配置静态资源
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Logger.debug("【配置静态资源拦截*****************************】配置不拦截静态资源被执行~~~~");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }



}
