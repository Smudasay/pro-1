package com.app7.say.application.Utils;

import java.text.SimpleDateFormat;

/**
 * Created by user on 19/11/2561.
 */

public class TimeUtils {

    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;
    private int second;

    public TimeUtils(){
        updateTime();
    }

    public void updateTime(){
        long currentMs = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String[] currentTime = sdf.format(currentMs).split(" ");
        String[] date = currentTime[0].split("/");
        String[] time = currentTime[1].split(":");

        year = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        day = Integer.parseInt(date[2]);

        hour = Integer.parseInt(time[0]);
        minute = Integer.parseInt(time[1]);
        second = Integer.parseInt(time[2]);
    }

    public int getYear() {
        updateTime();
        return year;
    }

    public int getMonth() {
        updateTime();
        return month;
    }

    public int getDay() {
        updateTime();
        return day;
    }

    public int getHour() {
        updateTime();
        return hour;
    }

    public int getMinute() {
        updateTime();
        return minute;
    }

    public int getSecond() {
        updateTime();
        return second;
    }
}
