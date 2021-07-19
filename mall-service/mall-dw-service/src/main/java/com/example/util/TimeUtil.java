package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-19 11:27
 */

public class TimeUtil {

    public static final String format1 = "yyyy-MM-dd HH:mm:ss";
    public static final String format2 = "yyyy-MM-dd";
    public static final String format3 = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String format4 = "yyyy年MM月dd日";
    public static final String unit_hour = "hour";
    public static final String unit_day = "day";

    /***
     * 当前时间增加N Unit
     */
    public static String beforeTime(String unit,Integer num){
        //1小时为单位
        long times = 3600000;
        if(unit.equalsIgnoreCase(unit_hour)){
            times=times*num;
        }else if(unit.equalsIgnoreCase(unit_day)){
            times=times*24*num;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format1);
        return simpleDateFormat.format( new Date(System.currentTimeMillis()-times));
    }
}
