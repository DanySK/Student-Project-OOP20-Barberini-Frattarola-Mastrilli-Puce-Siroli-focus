package oop.focus.calendar.controller;

import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.view.EventViewImpl;
import oop.focus.calendar.view.HoursViewImpl;
import oop.focus.common.Controller;



public interface CalendarDayController extends Controller {

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
     * Add daily events.
     * @return string
     */
    String writeDailyEvent();

}
