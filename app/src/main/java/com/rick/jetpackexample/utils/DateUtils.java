package com.rick.jetpackexample.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getDateStringAtNow() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
        String dateString = dateFormat.format(date);
        return dateString;
    }
}
