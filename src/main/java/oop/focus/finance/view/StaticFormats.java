package oop.focus.finance.view;

import javafx.scene.paint.Color;
import oop.focus.calendar.persons.model.Person;
import org.joda.time.LocalDateTime;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public final class StaticFormats {

    private static final int RGB_MAX_VALUE = 255;

    private StaticFormats() { }

    /**
     * Formats an amount in a formatted a string.
     *
     * @param amount in euro
     * @return a formatted version of amount
     */
    public static String formatAmount(final double amount) {
        final DecimalFormat f = new DecimalFormat("#0.00");
        return f.format(amount);
    }

    /**
     * Format a number into a two-digit number.
     *
     * @param number to format
     * @return the formatted version of the number.
     */
    public static String formatTwoDigits(final int number) {
        final DecimalFormat f = new DecimalFormat("#00");
        return f.format(number);
    }

    /**
     * Format a LocalDateTime in a formatted version for better reading.
     *
     * @param dateTime to format
     * @return the formatted version of dateTime
     */
    public static String formatDate(final LocalDateTime dateTime) {
        return dateTime.getYear() + "/" + dateTime.getMonthOfYear() + "/" + dateTime.getDayOfMonth() + "  "
                + formatTwoDigits(dateTime.getHourOfDay()) + ":" + formatTwoDigits(dateTime.getMinuteOfHour());
    }

    /**
     * Format a list of persons in a formatted version for better reading.
     *
     * @param persons to format
     * @return the formatted version of the person list
     */
    public static String formatPersonList(final List<Person> persons) {
        return persons.stream().map(Person::getName).collect(Collectors.joining(", "));
    }

    /**
     * @param color of which we want the RGB code
     * @return RGB code of the color
     */
    public static String formatColor(final Color color) {
        return String.format("%02X%02X%02X", (int) (color.getRed() * RGB_MAX_VALUE),
                (int) (color.getGreen() * RGB_MAX_VALUE), (int) (color.getBlue() * RGB_MAX_VALUE));
    }
}
