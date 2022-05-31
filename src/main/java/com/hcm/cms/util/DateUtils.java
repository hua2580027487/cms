package com.hcm.cms.util;

import com.alipay.service.schema.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static Logger logger = Logger.getLogger(DateUtils.class);
    public static final long ONE_DAY_MILLISECONDS = 24 * 60 * 60 * 1000;
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_DAY_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_MONTH_PATTERN = "yyyy年MM月";
    public static final String DEFAULT_MONTH_NO_CH_PATTERN = "yyyyMM";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm";
    public static final String DEFAULT_DATE_ALL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_ALL_PATTERN = "HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyyMMddHHmmss";
    public static final String DEFAULT_DAY_OTHER_PATTERN = "yyyy/MM/dd";
    public static final String DEFAULT_DATE_OTHER_ALL_PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final String DEFAULT_DAY_AND_MONTH = "MM-dd";
    public static final String DEFAULT_DAY_AND_MONTH_2 = "MM月dd日";
    public static final String DEFAULT_DATE_PATTERN_AND_MILLISECOND = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String DEFAULT_YEAR_MONTH = "yyyy-MM";
    public static final String DEFAULT_YEAR = "yyyy";
    public static final String DEFAULT_TIMEZONE = "GMT+8";


    public static Date parseDate(String str, String pattern) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        Date dt = null;
        DateFormat dtFmt = new SimpleDateFormat(pattern, Locale.US);
        try {
            new Date();
            dt = new Date(dtFmt.parse(str).getTime());
        } catch (Exception ex) {
            logger.error("Parser Exception: Invalid Date or pattern!");
        }
        return dt;
    }

    public static Date parseDate(Date date, String pattern) {
        return parseDate(getFormatDateStr(date, pattern), pattern);
    }

    public static String getDefaultFormatDateStr(Date date) {
        return getFormatDateStr(date, DEFAULT_TIME_ALL_PATTERN);
    }

    public static String getFormatDateStr(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        DateFormat dtFmt = new SimpleDateFormat(pattern, Locale.US);
        return dtFmt.format(date);
    }

    public static Calendar getOffsetDateForGivenDate(Date givenDate, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(givenDate);
        cal.add(Calendar.DATE, offset);
        return cal;
    }

    public static Calendar getOffsetMinuteForGivenDate(Date givenDate, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(givenDate);
        cal.add(Calendar.MINUTE, offset);
        return cal;
    }

    public static String getWeekDay(Date time) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }


    /**
     * 获取当前时间是一周的第几天 对应医生座诊星期
     *
     * @param time
     * @return
     */
    public static int getDayOfWeek(Date time) {
        Integer[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    public static String getDayNameOfWeek(Date time) {
        if (time == null) return "";
        String[] weekDays = {"周天", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }


    /**
     * 获取当前时间是一天的 上午 下午 晚上 对应医生座诊时间
     *
     * @param today
     * @return
     */
    public static int getDayType(Date today) {
        Integer nowHours = today.getHours();
        if (nowHours < 12) {
            return 1;
        } else if (nowHours < 18) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * 传入时间返回时间戳(秒) **
     */
    public static long convertTimeStamp(Date date) {
        if (date == null) {
            return 0;
        }
        return date.getTime() / 1000;
    }

    /**
     * 传入时间戳(秒)返回时间 **
     */
    public static Date convertDate(long millSec) {
        return new Date(millSec * 1000);
    }

    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    public static String convertDateStr(long millSec) {
        DateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd");
        return fmtDateTime.format(new Date(millSec * 1000));
    }

    public static Date extractionDate(long millSec) {
        Date in = new Date(millSec * 1000);
        DateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd");
        String text = fmtDateTime.format(in);
        ParsePosition pos = new ParsePosition(0);
        return fmtDateTime.parse(text, pos);
    }

    private static int[] monthToYear(int month) {
        int[] ret = new int[]{0, 0};
        ret[0] = month / 12;
        if (month % 12 != 0) {
            ret[1] = month % 12;
        }
        return ret;
    }

    public static String monthToYearStr(int month) {
        int[] ret = monthToYear(month);
        String str = ret[0] + "岁";
        if (ret[0] <= 5) {
            if (ret[1] != 0) {
                str += ret[1] + "个月";
            }
        }
        return str;
    }

    /**
     * ** 根据传入的时间和格式获得字符串 *****
     */
    public static String getDateStrByFormat(Date date, String format) {
        if (date == null) {
            return "";
        }
        DateFormat dtFmt = new SimpleDateFormat(format);
        return dtFmt.format(date);
    }

    private static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }

//    private static String getFormatTimeInTodayCase(Date date) {
//        Date today = Calendar.getInstance().getTime();
//        if (isSameDate(date, today)) {
//            DateFormat dtFmt = new SimpleDateFormat(UtilConstant.DEFAULT_TIME_PATTERN);
//            return "今天 " + dtFmt.format(date);
//        }
//        DateFormat dtFmt = new SimpleDateFormat(UtilConstant.DEFAULT_DATE_TIME_PATTERN);
//        return dtFmt.format(date);
//    }

//    public static String getFormatTimeInTodayCase(long date) {
//        return getFormatTimeInTodayCase(DateUtils.convertDate(date));
//    }

    /**
     * ** 根据传入的时间字符串和格式获得Date对象 *****
     */
    public static Date getDateByFormat(String date, String format) {
        if (StringUtil.isEmpty(date)) {
            return null;
        }
        DateFormat dtFmt = new SimpleDateFormat(format);
        Date dateTime = null;
        try {
            dateTime = dtFmt.parse(date.trim());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return dateTime;
    }

    /**
     * ** 根据传入的时间字符串和格式获得Date对象 *****
     */
    public static Date getDateByDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        String dateStr = getDateStrByFormat(date, format);
        return getDateByFormat(dateStr, format);
    }

    /**
     * *** 传入时间戳(秒),计算2个时间差多少天(自然天，不是24小时，如今天凌晨0点1分和昨天23点59分相差为1天) *******
     */
    public static long getDistanceDay(long date1, long date2) {
        String dateStr1 = getDateStrByFormat(new Date(date1 * 1000), "yyyy-MM-dd");
        String dateStr2 = getDateStrByFormat(new Date(date2 * 1000), "yyyy-MM-dd");

        Date date3 = getDateByFormat(dateStr1, "yyyy-MM-dd");
        Date date4 = getDateByFormat(dateStr2, "yyyy-MM-dd");

        long newDate1 = convertTimeStamp(date3);
        long newDate2 = convertTimeStamp(date4);
        long diff;
        if (newDate1 >= newDate2) {
            diff = newDate1 - newDate2;
        } else {
            diff = newDate2 - newDate1;
        }
        return diff / (60 * 60 * 24);
    }

    /**
     * *** 传入时间戳,计算2个时间差多少秒 *******
     */
    public static int getDistanceSecond(long date1, long date2) {
        long diff = date1 - date2;
        double diffDouble = diff / 1000;
        return (int) diffDouble;
    }


    /**
     * *** 根据生日算年龄 *******
     */
    public static Integer getAge(Date birthday) {
        if (birthday == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        calendar.setTime(birthday);
        int yearBirth = calendar.get(Calendar.YEAR);
        if (yearNow - yearBirth < 0) {
            return null;
        }
        return yearNow - yearBirth;
    }


    /**
     * 计算当前日期和指定日期年份差
     *
     * @param dateStr
     * @return
     */
    public static Integer getAge(String dateStr) {
        Date date = getDateByFormat(dateStr, DEFAULT_DAY_PATTERN);
        return getAge(date);
    }


    /**
     * *** 传入两个时间求之间天数 *******
     */
    public static int getDayCount(Date startDate, Date endDate) {
        startDate = parseDate(startDate, DateUtils.DEFAULT_DAY_PATTERN);
        endDate = parseDate(endDate, DateUtils.DEFAULT_DAY_PATTERN);
        Long betweenDate = (endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000);
        return betweenDate.intValue();
    }

    /**
     * 获取两个日期之间有间隔多少秒
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer getSecondOfTwoDate(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("date can not null");
        }
        Long betweenDate = (endDate.getTime() - startDate.getTime()) / 1000;
        return betweenDate.intValue();
    }

    /**
     * 获得周一到周七的文本 **
     */
    public static String getWeekDayStr(Integer weekDay) {
        String str = "周";
        switch (weekDay) {
            case 1:
                str += "一";
                break;
            case 2:
                str += "二";
                break;
            case 3:
                str += "三";
                break;
            case 4:
                str += "四";
                break;
            case 5:
                str += "五";
                break;
            case 6:
                str += "六";
                break;
            case 7:
                str += "日";
                break;
        }
        return str;
    }

    /**
     * 获得周上午下午晚上的文本 **
     */
    public static String getDayTypeStr(Integer dayType) {
        String str = "";
        switch (dayType) {
            case 1:
                str += "上午";
                break;
            case 2:
                str += "下午";
                break;
            case 3:
                str += "晚上";
                break;
        }
        return str;
    }

    /**
     * *** 根据传入时间判断是否为当天 *******
     */
    public static boolean isToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int startDayCount = (int) calendar.getTimeInMillis() / 86400000;
        calendar.setTime(date);
        int endDayCount = (int) calendar.getTimeInMillis() / 86400000;
        return endDayCount == startDayCount;
    }

    /**
     * *** 根据传入时间和相差天数计算结果Date *******
     */
    public static Date getDayByDateDistance(Date date, int distanceDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, distanceDay); // 日期减1
        Date lastDay = calendar.getTime(); // 结果
        return lastDay;
    }

    /**
     * *** 根据传入时间和相差秒数计算结果Date *******
     */
    public static Date getTimeByTimeAndDiffSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        Date lastDay = calendar.getTime(); // 结果
        return lastDay;
    }

    public static int getCurrentTime() {
        return Long.valueOf(new Date().getTime() / 1000).intValue();
    }

    public static Long getTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(date.getTime() / 1000).longValue();
    }

    public static String getDateStrByTimeInt(Integer time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Long timeLong = Long.parseLong(time + "");
        Date dt = new Date(timeLong * 1000);
        String sDateTime = sdf.format(dt);
        return sDateTime.toString();
    }

    private static Date increaseDate(Date time, int unit, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(unit, calendar.get(unit) + value);
        return calendar.getTime();
    }

    public static Date increaseDate(Date time, int value) {
        return increaseDate(time, Calendar.MINUTE, value);
    }

    /**
     * **** 传入时间，获得从这天起的连续7天的DateModel *******
     */
    public static List<DateModel> get7dateList(Date startDate) {
        List<DateModel> dateList = null;
        if (startDate != null) {
            dateList = new ArrayList<>();
            dateList.add(new DateModel(startDate));
            for (int i = 1; i <= 6; i++) {
                startDate = getDayByDateDistance(startDate, 1);
                dateList.add(new DateModel(startDate));
            }
        }
        return dateList;
    }


    public static boolean isOverlap(Date dt1Start, Date dt1End, Date dt2Start, Date dt2End) {
        if (dt1Start == null || dt1End == null || dt2Start == null || dt2End == null) {
            throw new IllegalArgumentException("param error");
        }
        long dt1StartLong = dt1Start.getTime();
        long dt1EndLong = dt1End.getTime();
        long dt2StartLong = dt2Start.getTime();
        long dt2EndLong = dt2End.getTime();

        if (dt1StartLong >= dt1EndLong) {
            throw new IllegalArgumentException("param error,param1 must before param2");
        }
        if (dt2StartLong >= dt2EndLong) {
            throw new IllegalArgumentException("param error,param3 must before param4");
        }
        if (dt1EndLong <= dt2StartLong) {
            return false;
        } else if (dt1StartLong >= dt2EndLong) {
            return false;
        }
        return true;
    }

    static boolean isDateBetween(Date start, Date end, Date paramDate) {
        if (start == null || end == null || paramDate == null) {
            throw new IllegalArgumentException("param error");
        }
        long startLong = start.getTime();
        long endLong = end.getTime();
        long paramLong = paramDate.getTime();

        if (paramLong < startLong) {
            return false;
        } else if (paramLong > endLong) {
            return false;
        }
        return true;
    }

    /**
     * ***** 比较一个时间是否比另一个大 *******
     */
    public static boolean equalIsDateMoreThanAnother(Date date1, Date date2) {
        if (date1.getTime() >= date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDateExpired(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTime().before(new Date());
    }

    public static Date getToday() {
        Date date = new Date();
        Date today = DateUtils.parseDate(DateUtils.getFormatDateStr(date, DateUtils.DEFAULT_DAY_PATTERN), DateUtils.DEFAULT_DAY_PATTERN);
        return today;
    }

    public static Date getTodayStartTime(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getTodayEndTime(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }


    public static String getSpecialDayStart(int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DATE, instance.get(Calendar.DATE) - day);
        Date time = instance.getTime();
        return DateUtils.getFormatDateStr(DateUtils.getTodayStartTime(time), DateUtils.DEFAULT_DATE_ALL_PATTERN);
    }

    public static String getSpecialDayEnd(int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DATE, instance.get(Calendar.DATE) - day);
        Date time = instance.getTime();
        return DateUtils.getFormatDateStr(DateUtils.getTodayEndTime(time), DateUtils.DEFAULT_DATE_ALL_PATTERN);
    }

    /**
     * 设置当日时间(时分毫秒)
     *
     * @return
     */
    public static Date setTime(Date date, int hour, int minute, int second, int millisecond) {
        date = org.apache.commons.lang3.time.DateUtils.setHours(date, hour);
        date = org.apache.commons.lang3.time.DateUtils.setMinutes(date, minute);
        date = org.apache.commons.lang3.time.DateUtils.setSeconds(date, second);
        date = org.apache.commons.lang3.time.DateUtils.setMilliseconds(date, millisecond);
        return date;
    }

    /**
     * 获取指定日期的当天开始时间
     *
     * @param date
     * @return
     */
    public static Date getDateSatrtDateTime(Date date) {
        if (date == null) return date;
        date = setTime(date, 0, 0, 0, 0);
        return getDateByDate(date, DEFAULT_DATE_ALL_PATTERN);
    }

    /**
     * 获取指定日期的当天结束时间
     *
     * @param date
     * @return
     */
    public static Date getDateEndDateTime(Date date) {
        if (date == null) return date;
        date = setTime(date, 23, 59, 59, 0);
        return getDateByDate(date, DEFAULT_DATE_ALL_PATTERN);
    }

    public static String getDateSatrtDateTimeStr(Date date) {
        if (date == null) return null;
        date = setTime(date, 0, 0, 0, 0);
        return getDateStrByFormat(date, DEFAULT_DATE_ALL_PATTERN);
    }

    /**
     * 获取指定日期的当天结束时间
     *
     * @param date
     * @return
     */
    public static String getDateEndDateTimeStr(Date date) {
        if (date == null) return null;
        date = setTime(date, 23, 59, 59, 0);
        return getDateStrByFormat(date, DEFAULT_DATE_ALL_PATTERN);
    }


    public static void main(String[] args) {
//        Integer i = 11;
//        String ii = "12";
//        System.out.println(i.compareTo(Integer.parseInt(ii)) <= 0);
//        System.out.println(getDayOfWeek(new Date()));
        Date now = DateUtils.getDateByDate(new Date(), DateUtils.DEFAULT_YEAR);
        Date patientBirthday = DateUtils.addYears(now, (0 - 20));
        System.out.println(DateUtils.getDateStrByFormat(patientBirthday, DateUtils.DEFAULT_DAY_PATTERN));
    }

    /**
     * 获取日期字符串直接的所有日期 yyyy-MM-dd
     *
     * @param startTime
     * @param endTime
     * @param formart
     * @return
     */
    public static List<String> getDays(String startTime, String endTime, String formart) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formart);
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取日期字符串直接的所有日期 yyyy-MM
     *
     * @param y1
     * @param y2
     * @return
     */
    public static List<String> getMonths(String y1, String y2) {
        List<String> list = new ArrayList<String>();
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM").parse(y1);
            Date endDate = new SimpleDateFormat("yyyy-MM").parse(y2);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            // 获取开始年份和开始月份
            int startYear = calendar.get(Calendar.YEAR);
            int startMonth = calendar.get(Calendar.MONTH);
            // 获取结束年份和结束月份
            calendar.setTime(endDate);
            int endYear = calendar.get(Calendar.YEAR);
            int endMonth = calendar.get(Calendar.MONTH);
            for (int i = startYear; i <= endYear; i++) {
                String date = "";
                if (startYear == endYear) {
                    for (int j = startMonth; j <= endMonth; j++) {
                        if (j < 9) {
                            date = i + "-0" + (j + 1);
                        } else {
                            date = i + "-" + (j + 1);
                        }
                        list.add(date);
                    }

                } else {
                    if (i == startYear) {
                        for (int j = startMonth; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else if (i == endYear) {
                        for (int j = 0; j <= endMonth; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else {
                        for (int j = 0; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取上周星期几的日期
     *
     * @param dayOfWeek
     * @param weekOffset
     * @return
     */
    public static Date getDayOfWeek(int dayOfWeek, int weekOffset) {
        Calendar date = Calendar.getInstance(Locale.CHINA);
        //周数减一，即上周
        date.add(Calendar.WEEK_OF_MONTH, weekOffset);
        //日子设为周几
        date.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        //时分秒全部置0
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    /**
     * 获取当前系统时间的前一天日期
     *
     * @return
     */
    public static Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 根据日期字符串获取星期
     *
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }

    /**
     * 获取本周第一天时间
     *
     * @return
     */
    public static Date getWeekStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        return date;
    }

    /**
     * 获取本周最后一天时间
     *
     * @return
     */
    public static Date getWeekEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 获取指定日期后一天时间
     *
     * @param date
     * @return
     */
    public static Date getSpecifiedDayAfter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getWeekDate(int weekType) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.WEEK_OF_MONTH, 0);
        c.set(Calendar.DAY_OF_WEEK, weekType);
        return c.getTime();
    }

    public static Date getSundayOfThisWeek() {
        Calendar cal = Calendar.getInstance();
        //设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        //获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        return cal.getTime();
    }

    public static List<DateModel> get14dateList(Date startDate) {
        List<DateModel> dateList = null;
        if (startDate != null) {
            dateList = new ArrayList<>();
            dateList.add(new DateModel(startDate));
            for (int i = 1; i <= 14; i++) {
                startDate = getDayByDateDistance(startDate, 1);
                dateList.add(new DateModel(startDate));
            }
        }
        return dateList;
    }

    public static Date getSundayThisWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 获取上周一日期
     *
     * @return
     */
    public static Date lastMonday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 1 - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 7);
        return getFirstDayOfWeek(calendar.getTime(), 2);
    }

    /**
     * 得到某一天的该星期的第一日 00:00:00
     *
     * @param date
     * @param firstDayOfWeek 一个星期的第一天为星期几
     * @return
     */
    public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(firstDayOfWeek);//设置一星期的第一天是哪一天
        cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);//指示一个星期中的某天
        cal.set(Calendar.HOUR_OF_DAY, 0);//指示一天中的小时。HOUR_OF_DAY 用于 24 小时制时钟。例如，在 10:04:15.250 PM 这一时刻，HOUR_OF_DAY 为 22。
        cal.set(Calendar.MINUTE, 0);//指示一小时中的分钟。例如，在 10:04:15.250 PM 这一时刻，MINUTE 为 4。
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
