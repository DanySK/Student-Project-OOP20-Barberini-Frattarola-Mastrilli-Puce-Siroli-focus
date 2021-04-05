package oop.focus.calendar.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public interface CalendarView {

    /**
     * Used for get the calendar page.
     * @return HBox
     */
    HBox buildCalendarPage();

    /**
     * Used for create left panel buttons.
     * @param buttoncolumn : column for the button
     * @param panelcolumn : column for the windows of the button 
     * @param string : name of the button
     */
    void pageButton(VBox buttoncolumn, VBox panelcolumn, String string);
}
