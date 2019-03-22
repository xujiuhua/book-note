package com.github.book.dateandtime.tutorial01;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class LocalTimeMain {

    public static void main(String[] args) {

        LocalTime localTime = LocalTime.now();
        int hour = localTime.getHour();

        int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);

        int hour2 = localTime.get(ChronoField.HOUR_OF_DAY);


        System.out.println(hour);
        System.out.println(minute);
        System.out.println(hour2);

        int i = LocalDateTime.now().get(ChronoField.HOUR_OF_DAY);
        System.out.println(i);

        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println(now.plus(2, ChronoUnit.YEARS));
        now.with(lastDayOfMonth());
        now.with(nextOrSame(DayOfWeek.FRIDAY));

        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd= 1;
                    if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
                    if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                }
        );

        LocalDate ld = LocalDate.of(2018, 6,29);
        System.out.println(ld.with(nextWorkingDay));

    }
}
