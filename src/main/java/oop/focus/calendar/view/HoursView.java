package oop.focus.calendar.view;

import oop.focus.calendar.view.HoursViewImpl.Format;

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
     * @param format of the time ( hours or half hours)
     * 
     */
    void setFormat(Format format);

    /**
     * @return format of the time ( hours or half hours )
     * 
     */
    int getFormat();

}
