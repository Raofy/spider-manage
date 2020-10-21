package com.jin10.spidermanage.util;

import java.util.Date;

public class TimeUtil {
    /***
     * convert Date to cron ,eg.  "0 07 10 15 1 ? 2016"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    public static String formatDateByPattern(Date date, String dateFormat) {
        return null;
    }
}
