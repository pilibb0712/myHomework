package com.example.summer.utility.dateUtility;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateUtility {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter ID_FORMAT
            = DateTimeFormat.forPattern("yyyy-MM-dd_HH-mm-ss-SSS");

    public static String convertIdToDate(String id){
        DateTime dateTime = DateTime.parse(id, ID_FORMAT);
        return dateTime.toString(DATE_FORMAT);
    }

    /**
     * 计算下一天
     * @author Mr.Wang
     * @param dayStr 已知日期
     * @return java.lang.String
     */
    public static String getNextDay(String dayStr) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(sdf.parse(dayStr));
            calendar.add(Calendar.DATE, 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
