package oop.focus.calendar.controller;


import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarMonthView;



public interface CalendarMonthController {

    /**
     * 
     * @return grid    Grid with the days
     */
    GridPane buildGridMonth();

    /**
     * 
     * 
     * @return month
     */
    List<DayImpl> getMonth();


    /**
     * @param monthview
     * @param flag
     * @param monthcontroller
     * @return changeMonthButton
     */
    EventHandler<ActionEvent> changeMonthButton(CalendarMonthView monthview, Boolean flag, CalendarMonthController monthcontroller);

    /**
     * @param monthview
     * @param monthcontroller
     */
    void updateView(CalendarMonthView monthview, CalendarMonthController monthcontroller);
}
