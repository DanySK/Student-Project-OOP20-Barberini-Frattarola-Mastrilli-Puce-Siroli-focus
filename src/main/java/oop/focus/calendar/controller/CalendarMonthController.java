package oop.focus.calendar.controller;


import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;
import oop.focus.calendar.view.HoursViewImpl.Format;



public interface CalendarMonthController {

    /**
     * Used for build the grid with the days of the month.
     * @return grid    Grid with the days
     */
    GridPane buildGridMonth();

    /**
     * 
     * Used to get the list with the days of the month.
     * @return month
     */
    List<DayImpl> getMonth();


    /**
     * Is an EventHandler for change the month (next or previous one).
     * @param monthview : the month view
     * @param flag : true previous month, false next month
     * @return EventHandler
     */
    EventHandler<ActionEvent> changeMonthButton(CalendarMonthViewImpl monthview, Boolean flag);

    /**
     * Used for update the view when there are changes.
     * @param monthview
     */
    void updateView(CalendarMonthView monthview);


    /**
     * Used for disable all the button of the month grid.
     * @param flag : true for disable or false for not
     */
    void disableButton(boolean flag);

    /**
     * 
     * @param format
     */
    void setFormat(Format format);

    /**
     * 
     * @param spacing
     */
    void setSpacing(double spacing);

}
