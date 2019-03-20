package com.github.book.jia.dateandtime.tutorial01;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

/**
 * <p></p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class InstantMain {
    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(1527435188), ZoneId.systemDefault());
//        System.out.println(localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

//        String format = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        System.out.println(LocalDateTime.now().format(dateTimeFormatter));
//        LocalDateTime parse = LocalDateTime.parse("2018-05-28 00:03", dateTimeFormatter);
//        System.out.println(parse);
//        System.out.println(format);
//        duration();
//        period();

//        ZoneId romeZone = ZoneId.systemDefault();
//        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
//        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
//        System.out.println(zdt1.toInstant());
//        System.out.println(date.atStartOfDay().atZone(romeZone).toInstant());

        // atStartOfDay(romeZone) --> atStartOfDay().atZone(romeZone)

//        Date date = new Date();
//        Instant instant = date.toInstant();
//        Date date1 = Date.from(instant);
//        System.out.println(date.equals(date1));

        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate.now().format(formatter);


        LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay(zoneId).toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        System.out.println(localDateTime);

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
        System.out.println(Instant.now().getEpochSecond());
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
