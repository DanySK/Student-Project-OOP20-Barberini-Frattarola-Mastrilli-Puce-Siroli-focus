package oop.focus.calendar.day.controller;

import oop.focus.calendar.day.view.HoursView;
import oop.focus.calendar.day.view.VBoxManager;
import oop.focus.calendar.model.Day;
import oop.focus.calendar.model.Format;
import oop.focus.common.Controller;


/**
 * Interface that models a day Controller.
 * Is used for set and get the format and the the spacing,
 * for get the daily events and write them in a string.
 */
public interface CalendarDayController extends Controller {

    /**
     * Used for build the day view.
     */
    void buildDay();

    /**
     * Used for get the hours box.
     * @return HoursView
     */
    HoursView getHoursBox();

    /**
     * Used for get the event box.
     * @return VBoxManager
     */
    VBoxManager getEventBox();

    /**
     * Used for get the day.
     * @return Day
     */
    Day getDay();

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
     * @param spacing : space between two hours in the {@link HoursView}
     */
    void setSpacing(double spacing);

    /**
     * Used for get the spacing of the hours.
     * @return double
     */
    double getSpacing();

    /**
     * Used for set the format of the hours.
     * @param format : the {@link Format} of the hours of the hours box
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
