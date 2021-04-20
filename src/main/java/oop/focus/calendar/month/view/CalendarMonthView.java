package oop.focus.calendar.month.view;

import javafx.scene.layout.VBox;
import oop.focus.common.View;

/**
 * Interface that models an Month View.
 * An Month View is a windows where you can build an Month of the Calendar.
 */
public interface CalendarMonthView extends View {

    /**
     * Used for update the view when there are changes.
     */
    void updateView();


    /**
     * Used for get the month view box.
     * @return vbox : the box that contain all the object of the month view
     */
    VBox getMonthView();

}
