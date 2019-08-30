package com.free.zdp.common;

public class ThreadLocalCommon {
    //开始时间
    public static  ThreadLocal<Long>_startTime=new ThreadLocal<>();
    //结束时间
    public static  ThreadLocal<Long>_endTime=new ThreadLocal<>();
    //存放地址
    public static ThreadLocal<String> _url_key = new ThreadLocal<String>();
}
