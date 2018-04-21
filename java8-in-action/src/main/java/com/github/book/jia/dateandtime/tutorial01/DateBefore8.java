package com.github.book.jia.dateandtime.tutorial01;

import java.util.Date;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class DateBefore8 {
    public static void main(String[] args) {
        // the years start from 1900, the month start at index 0
        // print: Tue Mar 18 00:00:00 CST 2014
        Date date = new Date(114, 2, 18);
        System.out.println(date);
    }
}
