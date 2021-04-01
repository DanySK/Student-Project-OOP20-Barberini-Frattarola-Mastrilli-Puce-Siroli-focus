package oop.focus.calendar.controller;

import oop.focus.calendar.view.HoursViewImpl.Format;

public interface CalendarOptionsController {

    /**
     * 
     * @param format
     */
    void setFormat(Format format);

    /**
     * @return format
     */
    Format getFormat();

    /**
     * 
     * @param spacing
     */
    void setSpacing(String spacing);

    /**
     * @return format
     */
    double getSpacing();
}
