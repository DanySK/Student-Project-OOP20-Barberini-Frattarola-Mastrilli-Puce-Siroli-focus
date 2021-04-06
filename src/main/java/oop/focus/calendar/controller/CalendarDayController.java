package oop.focus.calendar.controller;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.EventViewImpl;
import oop.focus.calendar.view.HoursViewImpl;
import oop.focus.calendar.view.HoursViewImpl.Format;

public interface CalendarDayController {

    /**
     * Used for build the day view.
     */
    void buildDay();

    /**
     * Used for get the hours box.
     * @return HoursViewImpl
     */
    HoursViewImpl getHoursBox();

    /**
     * Used for get the event box.
     * @return EventViewImpl
     */
    EventViewImpl getEventBox();

    /**
     * Used for get the day.
     * @return DayImpl
     */
    DayImpl getDay();

    /**
     * Used for set the day box in a scrollable panel.
     * @param container : is the day box
     */
    void setScroller(VBox container);

    /**
     * Get an scrollable panel with all the object of the day.
     * @return scrollable view of the day
     */
    ScrollPane getScroller();

    /**
     * Used for set the container panel of the day view.
     * @param container : is the day box
     */
    void setContainer(VBox container);

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
     * Used for get the spacing of the hours.
     * @return double
     */
    double getSpacing();

    /**
     * Used for set the format of the hours.
     * @param format
     */
    void setFormat(Format format);

    /**
     * Used for get the format of the hours.
     * @return Format
     */
    Format getFormat();

    /**
     * Used for get the string where are written the daily events.
     * @return String
     */
    String getDailyEvent();

    /**
     * Add daily events.
     */
    void setDailyEvent();

}
