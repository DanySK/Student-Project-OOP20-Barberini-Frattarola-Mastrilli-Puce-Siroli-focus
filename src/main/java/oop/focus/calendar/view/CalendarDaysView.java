package oop.focus.calendar.view;

import javafx.scene.control.ScrollPane;


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
    ScrollPane getContainer();

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
