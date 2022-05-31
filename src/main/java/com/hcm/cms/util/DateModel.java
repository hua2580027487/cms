package com.hcm.cms.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DateModel implements Serializable {
    private int day;
    private int year;
    private int month;
    private int weekDay;
    private int status;
    private String weekDayName;
    private String dateStr;

    public DateModel(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        setDay(cal.get(Calendar.DATE));
        setYear(cal.get(Calendar.YEAR));
        int month = cal.get(Calendar.MONTH);
        if (month == 12) {
            month = 1;
        } else {
            month++;
        }
        setMonth(month);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        if (weekDay == 1) {
            weekDay = 7;
        } else {
            weekDay--;
        }
        setWeekDay(weekDay);
        setDateStr(DateUtils.getDateStrByFormat(date, DateUtils.DEFAULT_DAY_PATTERN));
    }

    public String getYearMonthDayStr() {
        String ymdStr = getYear() + "";
        if (this.month < 10) {
            ymdStr += "-0" + this.month;
        } else {
            ymdStr += "-" + this.month;
        }
        if (this.day < 10) {
            ymdStr += "-0" + this.day;
        } else {
            ymdStr += "-" + this.day;
        }
        return ymdStr;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getWeekDayName() {
        return weekDayName;
    }

    public void setWeekDayName(String weekDayName) {
        this.weekDayName = weekDayName;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}
