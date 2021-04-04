package oop.focus.calendar.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import oop.focus.calendar.view.HoursViewImpl.Format;


public interface CalendarDaysView {

    /**
     * Used for create day view for the Calendar.
     *
     */
    void buildDay();

    /**
     * Get an scrollable panel with all the object of the day.
     * @return scrollable view of the day
     */
    ScrollPane getScroller();

    /**
     * Get the VBox with all the object of the day.
     * @return VBox view of the day
     */
    VBox getContainer();

    /**
     * Get the width of the day view.
     * @return width
     */
    double getWidth();

    /**
     * Get the height of the day view.
     * @return height
     */
    double getHeight();

    /**
     * Used for set the spacing between the hours.
     * @param spacing
     */
    void setSpacing(double spacing);

    /**
     * Used for the the format of the hours.
     * @param format
     */
    void setFormat(Format format);
}
