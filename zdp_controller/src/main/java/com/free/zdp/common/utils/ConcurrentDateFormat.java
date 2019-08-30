package com.free.zdp.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConcurrentDateFormat {

    private static final ThreadLocal<SimpleDateFormat>  _threadLocal=new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(TimeFormatTypes.YYYY_MM_dd_HH_mm_ss.getSource());
        }
    };

    private static final ThreadLocal<SimpleDateFormat>  _threadLocal_yymmdd=new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(TimeFormatTypes.YYYY_MM_dd.getSource());
        }
    };


    private static final ThreadLocal<SimpleDateFormat>  _threadLocal_yyyymm=new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(TimeFormatTypes.YYYY_MM.getSource());
        }
    };

    private static final ThreadLocal<SimpleDateFormat>  _threadLocal_parse_yyyymm=new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(TimeFormatTypes.YYYY_MM.getSource());
        }
    };

    /**
     * 解析字符串年月
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseTimeToDateyymm(String date) throws ParseException {
        return _threadLocal_parse_yyyymm.get().parse(date);
    }

    public static String formatDateTimeyymm(Date date){
        return _threadLocal_yyyymm.get().format(date);
    }
    /**
     * 格式化时间
     */

    public static String formatDateTime(Date date){
        return _threadLocal.get().format(date);
    }

    /**
     * 年月日
     * @param date
     * @return
     */
    public static String formatDateTimetoYmd(Date date){
        return _threadLocal_yymmdd.get().format(date);
    }



    enum TimeFormatTypes {
        YYYY_MM_dd("yyyy-MM-dd"),
        YYYY_MM("yyyyMM"),
        YYYY_MM_dd_HH_mm_ss_SSS("yyyy-MM-dd HH:mm:ss.SSS"),
        YYYY_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
        yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS");

        TimeFormatTypes(String source) {
            this.source = source;
        }

        private String source;

        public String getSource() {
            return source;
        }
    }

}
