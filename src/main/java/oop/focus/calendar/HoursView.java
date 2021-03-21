package oop.focus.calendar;

import oop.focus.calendar.HoursViewImpl.Format;

public interface HoursView {

    /**
     * Get spacing between two number.
     * @return spacing
     */
    double getSpacing();

    /**
     * @param spacing between two number
     * 
     */
    void setSpacing(double spacing);

    /**
     * @param format of the time hours or half
     * 
     */
    void setFormat(Format format);

    /**
     * @return format of the time hours or half
     * 
     */
    int getFormat();

}
