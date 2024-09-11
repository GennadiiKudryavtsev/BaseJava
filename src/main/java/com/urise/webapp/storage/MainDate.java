package com.urise.webapp.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainDate {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Date date = new Date();
        System.out.println(date);
        System.out.println(System.currentTimeMillis() - start);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/Los-_Angeles"));
        System.out.println(calendar.getTime());

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldlt = LocalDateTime.of(ld, lt);
        System.out.println(ld);
        System.out.println(lt);
        System.out.println(ldlt);

    }
}
