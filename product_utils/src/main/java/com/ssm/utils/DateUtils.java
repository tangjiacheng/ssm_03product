package com.ssm.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: TJC
 * @Date: 2020/6/6 16:32
 * @description:
 * 将日期转换成字符串\
 * 将字符串转换成日期
 */
public class DateUtils {
    public static String date2String(Timestamp date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Timestamp string2Date(String str, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(str);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String s = sdf2.format(date) + ":00";
        return Timestamp.valueOf(s);

    }
}
