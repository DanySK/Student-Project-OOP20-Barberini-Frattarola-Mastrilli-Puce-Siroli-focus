package oop.focus.calendar;

import javafx.scene.control.ScrollPane;


public interface CalendarDaysLogic {

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
     * Get the width of the scroller.
     * @return width
     */
    int getWidth();

    /**
     * Get the height of the scroller.
     * @return height
     */
    int getHeight();
}
