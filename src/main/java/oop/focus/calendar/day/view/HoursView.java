package oop.focus.calendar.day.view;
import oop.focus.calendar.model.Format;

/**
 * Interface that models an HoursView.
 * An HoursView is a box where are all the hours of the day,
 * and is composed by an spacing (the space between two hours)
 * and an {@link Format} (format of the hours Normal ( 0, 1,  2) or Extended (0, 0.30, 1, 1.30))
 */
public interface HoursView extends VBoxManager {

    /**
     * Get the current spacing between two number.
     * @return double : the spacing
     */
    double getSpacing();

    /**
     * Used for set the spacing.
     * @param spacing between two hours
     */
    void setSpacing(double spacing);

    /**
     * Used for set the {@link Format} of the hours.
     * @param format of the time
     */
    void setFormat(Format format);

    /**
     * Get the current format.
     * @return format of the time ( hours or half hours )
     */
    int getFormat();

}
