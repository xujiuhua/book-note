package com.github.book.jia.dateandtime.tutorial01;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class InstantMain {
    public static void main(String[] args) {
        duration();
        period();
    }

    private static void duration() {
        LocalTime time1 = LocalTime.of(13, 13, 13);
        LocalTime time2 = LocalTime.of(13, 13, 14);
        Duration duration = Duration.between(time1, time2);
        System.out.println(duration.getSeconds());
    }

    private static void period() {
        LocalDate date1 = LocalDate.of(2018, 4, 25);
        LocalDate date2 = LocalDate.of(2018, 5, 4);
        Period period = Period.between(date1, date2);
        System.out.println(period.getDays());
    }

    private void createDuration() {
        Duration threeMins = Duration.ofMinutes(3);
        Duration fourMins = Duration.of(4, ChronoUnit.MINUTES);
    }

    private void createPeriod() {
        Period tenDays = Period.ofDays(10);
        Period nineDays = Period.of(0, 0, 9);
    }
}
