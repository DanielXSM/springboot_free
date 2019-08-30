package com.free.zdp.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.UUID;

import static com.free.zdp.common.ThreadLocalCommon.*;

@Aspect
@Component
public class LogAop {
    private static final Logger LOGGER= LoggerFactory.getLogger(LogAop.class);
    //service层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的

    public static final String POINTCUT="execution (* com.free.zdp.controller.*Controller.*(..))";
    private static  ObjectMapper objectMapper = new ObjectMapper();
    private String localIP = null;

    @Around(POINTCUT)


    public Object LogAround(ProceedingJoinPoint joinPoint){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 如果有session则返回session如果没有则返回null(避免创建过多的session浪费内存)
        HttpSession session = request.getSession(false);
        // 获取请求头
        Enumeration<String> enumeration = request.getHeaderNames();
        StringBuffer headers = new StringBuffer();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            headers.append(name + ":" + value).append(",");
        }
        String uri = UUID.randomUUID() + "|" + request.getRequestURI();
        String method = request.getMethod();
        StringBuffer params = new StringBuffer();
        if (HttpMethod.GET.toString().equals(method)) {// get请求
            String queryString = request.getQueryString();
            if (StringUtils.isNotBlank(queryString)) {
                try {
                    params.append(URLEncoder.encode(queryString, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } else { //其他请求
            Object[] paramsArray = joinPoint.getArgs();
            if (paramsArray != null && paramsArray.length > 0) {
                for (int i = 0; i < paramsArray.length; i++) {
                    if (paramsArray[i] instanceof Serializable) {
                        params.append(paramsArray[i].toString()).append(",");
                    } else {
                        //使用json系列化 反射等等方法 反系列化会影响请求性能建议重写tostring方法实现系列化接口
                        try {
                            String param= objectMapper.writeValueAsString(paramsArray[i]);
                            if(StringUtils.isNotBlank(param))
                                params.append(param).append(",");
                        } catch (JsonProcessingException e) {
                            LOGGER.error("doBefore obj to json exception obj={},msg={}",paramsArray[i],e);
                        }
                    }
                }
            }
        }
        _url_key.set(uri);
        LOGGER.info("request params>>>>>>uri={},method={},params={},headers={}", uri, method, params, headers);


        /**
         * 获取所有的参数
         */
        Object[] args = joinPoint.getArgs();
        //打印参数
        LOGGER.debug("********************入参:{}", Arrays.asList(args));
        long start_timeMillis = System.currentTimeMillis();
        _startTime.set(start_timeMillis);
        String result=null;
        Object proceed=null;
        try {
            //执行被拦截的方法
             proceed = joinPoint.proceed(args);
            if(proceed instanceof Serializable){
                result=proceed.toString();
            }else {
                if(null!=proceed){
                    result=objectMapper.writeValueAsString(proceed);
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long end_time = System.currentTimeMillis();
        long costTime = end_time - _startTime.get();
        _startTime.remove();
        String url_get = _url_key.get();
        String localIP = getLocalIP();
        String realIp = getRealIp(request);
        LOGGER.info("response result<<localIP={}<<realIp:{}<<uri={},result={},costTime={}ms",localIP, realIp,url_get, result, costTime);
        return proceed;
    }


    /**
     * 获取真实IP
     * @param request 请求体
     * @return 真实IP
     */
    public static String getRealIp(HttpServletRequest request) {
        // 这个一般是Nginx反向代理设置的参数
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 处理多IP的情况（只取第一个IP）
        if (ip != null && ip.contains(",")) {
            String[] ipArray = ip.split(",");
            ip = ipArray[0];
        }
        return ip;
    }

    private String getLocalIP() {

        if (localIP != null && localIP.length() > 0)
        {
            return localIP;
        }

        Enumeration<NetworkInterface> n;
        try {
            n = NetworkInterface.getNetworkInterfaces();
            for (; n.hasMoreElements();)
            {
                NetworkInterface e = n.nextElement();

                Enumeration<InetAddress> a = e.getInetAddresses();
                for (; a.hasMoreElements();)
                {
                    InetAddress addr = a.nextElement();
                    String ip =addr.getHostAddress();
                    LOGGER.debug("ip address" + ip);
                    if (addr.isSiteLocalAddress())
                    {
                        LOGGER.debug("got ip address" + ip);
                        localIP = ip.replace(".", "_");
                        return localIP;
                    }
                }
            }
        } catch (SocketException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return localIP;
    }
}
