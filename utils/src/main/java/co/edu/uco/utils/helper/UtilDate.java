package co.edu.uco.utils.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

import static co.edu.uco.utils.helper.UtilObject.*;

public final class UtilDate {

    private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
            .optionalStart()
            .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 9, true)
            .optionalEnd()
            .toFormatter();
    public static final LocalDateTime TIME = LocalDateTime.now();

    public static boolean isNull(Date date) {
        return isNullObject(date);
    }

    public static Date getDefaultIsNull(Date value) {
        return getDefaultIsNullObject(value, new Date());
    }

    public static LocalDateTime getDefaultTime(LocalDateTime value, LocalDateTime defaultValue) {
        return getDefaultIsNullObject(value,defaultValue);
    }
    public static LocalDateTime getDefaultTimeIfNull(LocalDateTime value) {
        return getDefaultTime(value,TIME);
    }

    public static boolean isBetween(Date date, Date init, Date end) {
        return (date.after(init) && date.before(end));
    }

    public static boolean isBefore(Date compare, Date date) {
        return compare.before(date);
    }

    public static boolean isBefore(Date compare) {
        return compare.before(getLocalDataTimeADate(TIME));
    }

    public static boolean isBetweenIncludingInit(Date date, Date init, Date end) {
        return (isBetween(date, init, end) || date.equals(init));
    }

    public static boolean isBetweenIncludingEnd(Date date, Date init, Date end) {
        return (isBetween(date, init, end) || date.equals(end));
    }

    public static boolean isBetweenIncludingRanges(Date date, Date init, Date end) {
        return (isBetweenIncludingEnd(date, init, end) || isBetweenIncludingInit(date, init, end));
    }

    public static LocalDate currentDate() {
        return LocalDate.now();
    }

    public static Date getLocalDateADate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate getDateALocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date getLocalDataTimeADate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getDateALocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static boolean isOfLegalAge(Date birthDate) {
        Period period = Period.between(getDateALocalDate(birthDate), currentDate());
        return !UtilNumeric.isLessThan(period.getYears(), 18);
    }

    public static LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, formatter);
    }

}
