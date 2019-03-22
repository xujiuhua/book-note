package com.github.book.dateandtime.tutorial01;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class Manipulate {
    public static void main(String[] args) {
        fun1();
        fun2();
    }

    /**
     * 1. absolute
     *
     * The most immediate and easiest way to create a modified version of an existing LocalDate is changing one of its attributes,
     * using one of its withAttribute methods
     */
    private static void fun1() {
        LocalDate date = LocalDate.of(2018, 4, 25);
        LocalDate date1 = date.withYear(2019);
        LocalDate date2 = date.with(ChronoField.MONTH_OF_YEAR, 5);
        System.out.println(date1);
        System.out.println(date2);
    }

    /**
     * 2. relative
     *
     */
    private static void fun2() {
        LocalDate date = LocalDate.of(2018, 4, 25);
        LocalDate date1 = date.plusWeeks(3);
        LocalDate date2 = date.minusYears(10);
        LocalDate date3 = date.plus(6, ChronoUnit.MONTHS);
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
    }

}
