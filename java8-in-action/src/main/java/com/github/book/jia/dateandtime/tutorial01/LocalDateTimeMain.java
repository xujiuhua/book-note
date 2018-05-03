package com.github.book.jia.dateandtime.tutorial01;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class LocalDateTimeMain {
    public static void main(String[] args) {

        // 1. direct
        LocalDateTime localDateTime = LocalDateTime.of(2018, 4, 21, 21, 55, 12);
        System.out.println(localDateTime);

        // 2. date and time
        LocalDate localDate = LocalDate.of(2018, 4, 21);
        LocalTime localTime = LocalTime.of(21, 55, 12);
        LocalDateTime localDateTime1 = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime1);

        // 3. passing a time to a LocalDate or conversely a date to a LocalTime
        LocalDate date = LocalDate.of(2018, 4, 21);
        LocalTime time = LocalTime.of(21, 55, 12);
        LocalDateTime dt1 = date.atTime(21, 55, 12);
        LocalDateTime dt2 = date.atTime(time);
        System.out.println(dt1);
        System.out.println(dt2);

        LocalDateTime dt4 = time.atDate(date);
        System.out.println(dt4);

        // 4. extract the LocalDate or LocalTime component from a LocalDateTime
        LocalDate date1 = dt1.toLocalDate();
        LocalTime date2 = dt2.toLocalTime();

        System.out.println(LocalDateTime.now());
        System.out.println(new Date());

    }
}
