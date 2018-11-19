package com.wen.web.lotterysystem.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author admin
 * @date 2018-11-19 10:26
 */
public class DateUtils {

    /**
     * 将时间格式转化指定的字符串格式
     *
     * @param pattern
     * @return
     */
    public static String formateDate(String pattern) {
        return formateDate(new Date(), pattern);
    }

    public static String formateDate(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
}
