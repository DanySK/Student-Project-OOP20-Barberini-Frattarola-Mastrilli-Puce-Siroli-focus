package oop.focus.calendar.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import oop.focus.calendar.view.HoursViewImpl.Format;


public interface CalendarDaysView {

    /**
     * Used for create Day for Calendar.
     *
     */
    void buildDay();

    /**
     * Get the scroller with all the object of the day.
     * @return scrollable view of the day
     */
    ScrollPane getScroller();

    /**
     * Get the VBox with all the object of the day.
     * @return VBox view of the day
     */
    VBox getContainer();

    /**
     * Get the width of the scroller.
     * @return width
     */
    double getWidth();

    /**
     * Get the height of the scroller.
     * @return height
     */
    double getHeight();

    /**
     * 
     * @param spacing
     */
    void setSpacing(double spacing);

    /**
     * 
     * @param format
     */
    void setFormat(Format format);
}
