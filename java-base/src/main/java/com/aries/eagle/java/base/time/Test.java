package com.aries.eagle.java.base.time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date date = Date.from(LocalDate.now().
                atTime(0, 0, 0).
                atZone(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(LocalDate.now().
                atTime(23, 59, 59).
                atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(date);
        System.out.println(date2);

    }
}
