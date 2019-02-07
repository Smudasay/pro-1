package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hijri {

    @SerializedName("date")
    private String date;
    @SerializedName("format")
    private String format;
    @SerializedName("day")
    private String day;
    @SerializedName("weekday")
    private Weekday_ weekday;
    @SerializedName("month")
    private Month_ month;
    @SerializedName("year")
    private String year;
    @SerializedName("designation")
    private Designation_ designation;
    @SerializedName("holidays")
    private List<Object> holidays = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Weekday_ getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday_ weekday) {
        this.weekday = weekday;
    }

    public Month_ getMonth() {
        return month;
    }

    public void setMonth(Month_ month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation_ getDesignation() {
        return designation;
    }

    public void setDesignation(Designation_ designation) {
        this.designation = designation;
    }

    public List<Object> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Object> holidays) {
        this.holidays = holidays;
    }

}
