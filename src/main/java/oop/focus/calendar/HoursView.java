package oop.focus.calendar;

import javafx.scene.layout.VBox;
import oop.focus.calendar.HoursViewImpl.Format;

public interface HoursView {

    /**
     * @param format of the time hours or half
     * 
     */
    void setSpacing(Format format);

    /**
     * @return  the position of the label
     * @param hour qualcosa part 2
     */
    double getY(int hour);

    /**
     *@param vbox set VBox hours.
     */
     void setVBox(VBox vbox);

    /**
     * @return get the hours box.
     */
     VBox getVBox();
}
