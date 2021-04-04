package oop.focus.calendar.controller;

import oop.focus.calendar.view.HoursViewImpl.Format;

public interface CalendarSettingsController {

    /**
     * Used for set the format of the hours.
     * @param format
     */
    void setFormat(Format format);

    /**
     * Used for get the format of the hours.
     * @return format
     */
    Format getFormat();

    /**
     * Used for check if the spacing isn't under a certain threshold and if is a number.
     * 
     * @param spacing : is the string of the TextField
     * @return true if pass the check or false if not
     */
    boolean checkSpacing(String spacing);

    /**
     * Used for set the space between the hours.
     * @param spacing
     */
    void setSpacing(double spacing);

    /**
     * Used for get the spacing between the hours.
     * @return spacing
     */
    double getSpacing();
}
