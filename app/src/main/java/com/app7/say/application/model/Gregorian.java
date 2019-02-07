package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

public class Gregorian {

    @SerializedName("date")
    private String date;
    @SerializedName("format")
    private String format;
    @SerializedName("day")
    private String day;
    @SerializedName("weekday")
    private Weekday weekday;
    @SerializedName("month")
    private Month month;
    @SerializedName("year")
    private String year;
    @SerializedName("designation")
    private Designation designation;

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

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

}
