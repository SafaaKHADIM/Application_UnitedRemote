package com.example.asus.internship.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
    Calendar calendar;
    int month;
    int year;
    int day ;
    public Date() {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        month = calendar.get(Calendar.MONTH) + 1;
        year  = calendar.get(Calendar.YEAR);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
