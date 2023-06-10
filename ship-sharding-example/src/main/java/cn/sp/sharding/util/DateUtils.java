package cn.sp.sharding.util;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/8
 */
public class DateUtils {


    /**
     * 获取某一年某个季度的开始和结束时间
     *
     * @param yearStr 如2023
     * @param quarter 如第一季度 1
     * @return
     */
    public static Long[] getMinAndMaxTime(String yearStr, String quarter) {
        int minMonth = (Integer.valueOf(quarter) - 1) * 3 + 1;
        int maxMonth = minMonth + 2;
        Integer year = Integer.valueOf(yearStr);
        LocalDateTime firstDateTime = LocalDateTime.of(year, minMonth, 1, 0, 0, 0);
        LocalDateTime lastDateTime = LocalDateTime.of(year, maxMonth, 1, 23, 59, 59);
        LocalDateTime dateTime = lastDateTime.with(TemporalAdjusters.lastDayOfMonth());
        Long[] arr = new Long[]{localDateTimeToLong(firstDateTime), localDateTimeToLong(dateTime)};
        return arr;
    }


    /**
     * LocalDateTime 转时间戳
     *
     * @param localDateTime
     * @return
     */
    public static Long localDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 时间戳转LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate longToLocalDate(Long date) {
        return Instant.ofEpochMilli(date).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }


    /**
     * 计算月份是当年第几个季度
     *
     * @param localDate
     * @return
     */
    public static Integer getQuarter(LocalDate localDate) {
        int monthValue = localDate.getMonthValue();
        int result = monthValue / 3;
        if (monthValue % 3 > 0) {
            result++;
        }
        return result;
    }
}
