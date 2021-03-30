package oop.focus.calendar.controller;

import java.util.List;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.view.CalendarDaysViewImpl;

public interface CalendarMonthController {

    /**
     * 
     * @param month    list with the days of the calendar
     * @param cells    Map that link the button to the day
     * @return grid    Grid with the days
     */
    GridPane buildGridMonth(List<DayImpl> month, Map<Button, CalendarDaysViewImpl> cells);

}
