package com.example.overseasshopping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static Date stringToDate(String a, String b) {
        int month = Integer.parseInt(a) - 1;
        int day = 1;
        int year = Integer.parseInt("20" + b);
        Date date =  Calendar.getInstance().getTime();
        return date;
    }

    public static String dateToString(Date date) {
        String displayDate;
        String displayMonth;
        String displayYear;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        if (month <= 9 ) {
            displayMonth = "0" + month;
        } else {
            displayMonth = Integer.toString(month);
        }

        displayYear = (Integer.toString(year)).substring(2, 4);

        displayDate = displayMonth + "/" + displayYear;

        return displayDate;
    }

    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String date = dateFormat.format(Calendar.getInstance().getTime());

        return date;
    }
}
