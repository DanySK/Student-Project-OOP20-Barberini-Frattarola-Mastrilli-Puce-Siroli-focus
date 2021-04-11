package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import oop.focus.common.View;


public interface CalendarMonthView extends View {

    /**
     * Used for build the grid with the days of the month.
     * @return grid    Grid with the days
     */
    GridPane buildGridMonth();

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
     * Used for get the month info label (years and name).
     * @return label
     */
    Label getMonthInfo();

    /**
     * Used for set the month view.
     * @param month view
     */
    void setMonthView(VBox month);


    /**
     * Used for get the month view box.
     * @return vbox
     */
    VBox getMonthView();



}
