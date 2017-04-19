package me.memory7734.moving.util;

import java.util.Calendar;

/**
 * Created by Elijah on 16/10/23.
 */

public class TimeUtils {
    public static Calendar getCalendarFromInt(int date) {
        int year = date / 10000;
        int month = date / 100 % 100;
        int day = date % 100;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        return calendar;
    }

    public static int getIntFromCalendar(Calendar calendar) {
        return calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DATE);
    }

    public static int add(int currentDate,int amount) {
        Calendar calendar = getCalendarFromInt(currentDate);
        calendar.add(Calendar.DATE, amount);
        return getIntFromCalendar(calendar);
    }
}
