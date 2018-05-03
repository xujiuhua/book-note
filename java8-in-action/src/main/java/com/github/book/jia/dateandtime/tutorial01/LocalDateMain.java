package com.github.book.jia.dateandtime.tutorial01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

/**
 * <p> LocalDate and LocalTime same principle </p>
 *
 * @author jiuhua.xu
 * @version 1.0
 * @since JDK 1.8
 */
public class LocalDateMain {

    private static final Logger log = LoggerFactory.getLogger(LocalDateMain.class);

    public static void main(String[] args) {
        base();
        useTemporalField();
        parseString();
    }

    /**
     * a plain date without the time of day.
     */
    public static void base() {
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        log.info("year is {}", localDate.getYear());
        log.info("month is {}", localDate.getMonth());
        log.info("day of year is {}", localDate.getDayOfYear());
        log.info("day of month is {}", localDate.getDayOfMonth());

        LocalDate now = LocalDate.now();
        log.info("current year is {}", now.getYear());
    }

    /**
     * The ChronoField enumeration implements this interface of TemporalField
     */
    public static void useTemporalField() {
        LocalDate date = LocalDate.now();
        log.info("year is {}", date.get(ChronoField.YEAR));
        log.info("month is {}", date.get(ChronoField.MONTH_OF_YEAR));
        log.info("day of year is {}", date.get(ChronoField.DAY_OF_MONTH));
    }

    /**
     * It’s intended as a replacement for the old java.util.DateFormat
     * more details DateTimeFormatter
     */
    public static void parseString() {
        LocalDate date = LocalDate.parse("2017-09-19");
        log.info("year is {}", date.get(ChronoField.YEAR));
    }
}
