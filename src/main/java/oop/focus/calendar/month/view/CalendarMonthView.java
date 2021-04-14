package oop.focus.calendar.month.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import oop.focus.common.View;

/**
 * Interface that models an Month View.
 * An Month View is a windows where you can build an Month of the Calendar.
 */
public interface CalendarMonthView extends View {

    /**
     * Used for build the grid with the days of the month.
     * @return grid    Grid with the days
     */
    GridPane buildGridMonth();

    /**
     * Is an EventHandler for change the month (next or previous one).
     * @param monthView : the month view
     * @param flag : true previous month, false next month
     * @return EventHandler
     */
    EventHandler<ActionEvent> changeMonthButton(CalendarMonthView monthView, Boolean flag);

    /**
     * Used for update the view when there are changes.
     * @param monthView
     */
    void updateView(CalendarMonthView monthView);


    /**
     * Used for set the month view.
     * @param month : the box that will contain all the object of the month view
     */
    void setMonthView(VBox month);


    /**
     * Used for get the month view box.
     * @return vbox : the box that contain all the object of the month view
     */
    VBox getMonthView();



}
