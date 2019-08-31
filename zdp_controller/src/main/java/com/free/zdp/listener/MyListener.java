package com.free.zdp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.URL;

/**
 * 监视器
 */
@WebListener
@ServletComponentScan()
public class MyListener implements ServletContextListener {
    private static Logger LOG = LoggerFactory.getLogger(MyListener.class);

    /**
     * 初始化
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ClassLoader classLoader = sce.getServletContext().getClassLoader();
//        URL resource = classLoader.getResource("classpath:application.properties");
//        String path = resource.getPath();
//        LOG.info("######################【MyListener】######################################### 配置文件路径={}...",path);
        LOG.info("######################【MyListener】######################################### 初始化...");
    }


    /**
     * 销毁
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("######################【MyListener】#########################################  销毁...");
    }
}
