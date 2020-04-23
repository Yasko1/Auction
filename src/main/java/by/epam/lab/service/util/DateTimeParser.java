package by.epam.lab.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Designed for date and time parsing.
 */
public class DateTimeParser {

    private static final String PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * The method parsing {@link String} object in "yyyy-MM-dd HH:mm" pattern into {@link Date} object.
     */
    public static Date parse(String dateTime) {
        dateTime = dateTime.replace('T', ' ');
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(PATTERN);
        Date date;
        try {
            date = format.parse(dateTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Illegal date format!");
        }
        return date;
    }

    /**
     * The method parsing {@link Date} object into {@link String} object in "yyyy-MM-dd HH:mm" pattern.
     */
    public static String parse(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
        return dateFormat.format(date);
    }
}
